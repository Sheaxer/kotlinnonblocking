package fei.stuba.gono.kotlin.nonblocking.security

import fei.stuba.gono.kotlin.security.SecurityConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
/***
 * Class setting up web security - exposes POST operation access to endpoint /login and to /signup
 * without authorization, and requires valid JWT to access every other endpoint.
 * Trieda, ktorá nastavuje webovú bezpečnosť - povolí prístup cez POST operáciu na endpoint-y
 * /login a /signup, a nastaví vyžadovanie správneho JWT pre prístup k ostatným endpoint-om.
 */
@Configuration
class SecurityConfigurationImpl @Autowired constructor(
        private val securityContextRepository: SecurityContextRepository,
        private val reactiveAuthenticationManager: ReactiveAuthenticationManager
){
    /***
     * Sets up security chain - if not authorized returns Http code 401 - Unauthorized, when fails
     * the authorization process returns HTTP code 403 - Forbidden, disables the default authorization methods
     * and adds instances of SecurityContextRepository and JWTAuthenticationManager to handle authorization to system.
     * Permits access to /login and to /signup endpoints and prevents unauthorized access to all other endpoints.
     * Nastaví reťaz bezpečnostných filtrov: pri prístupu do systému bez autorizácie vráti HTTP kód 401
     * - Unauthorized, pri nesprávnej autorizácii vráti HTTP kód 403 - Forbidden, zakáže použitie prednastavených
     * autorizačných metód a pridá inštancie tried SecurityContextRepository a JWTAuthenticationManager ako správcov
     * autorizácie do systému. Umožní neautorizovaný prístup k /login a /signup endpointom, a zakáže neautorizaovaný
     * prístup ku všetkým ostatným endpointom.
     * @param http
     * @return configured security filter chain.
     * konfigurovaná reťaz bezpečnostných filtrov.
     */
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        return http.exceptionHandling()
                .authenticationEntryPoint { serverWebExchange: ServerWebExchange,
                                            e: AuthenticationException? -> Mono.fromRunnable {
                    serverWebExchange.response.statusCode = HttpStatus.UNAUTHORIZED } }.
                accessDeniedHandler { serverWebExchange: ServerWebExchange, e: AccessDeniedException? ->
                    Mono.fromRunnable { serverWebExchange.response.statusCode = HttpStatus.FORBIDDEN } }
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(reactiveAuthenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
                .pathMatchers(HttpMethod.POST, "/login").permitAll()
                .anyExchange().authenticated()
                .and().build()
    }
}