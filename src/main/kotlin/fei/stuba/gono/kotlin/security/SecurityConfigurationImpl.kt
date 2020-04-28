package fei.stuba.gono.kotlin.security

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

@Configuration
class SecurityConfigurationImpl @Autowired constructor(
        private val securityContextRepository: SecurityContextRepository,
        private val reactiveAuthenticationManager: ReactiveAuthenticationManager
){
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