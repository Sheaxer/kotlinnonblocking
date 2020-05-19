package fei.stuba.gono.kotlin.nonblocking.security

import fei.stuba.gono.kotlin.security.SecurityConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
/***
 * Class implementing ServerSecurityContextRepository - loads
 * Authorization header of the HTTP request and checks if it contains valid non expired JWT.
 * Trieda implementujúca ServerSecurityContextRepository - načíta Authorization hlavičku
 * z HTTP požiadavky a skontroluje či obsahuje korektný a neexpirovaný JWT.
 * @see ServerSecurityContextRepository
 * @param authenticationManager Performs the authentication on the JWT.
 * Vykoná overenie JWT.
 */
@Component
class SecurityContextRepository @Autowired constructor(
        private val authenticationManager: ReactiveAuthenticationManager): ServerSecurityContextRepository {


    override fun save(p0: ServerWebExchange?, p1: SecurityContext?): Mono<Void> {
        TODO("Not yet implemented")
    }

    /***
     * Retrieves the Authorization header of HTTP request, checks if it contains
     * JWT and validates it.
     * Načíta Authorization sekciu hlavičky HTTP požiadavky, skontroluje či táto sekcia
     * obsahuje JWT a validuje JWT.
     * @param p0 provides access to HTTP request and response.
     *                          poskytuje prístup k HTTP požiadavke a odpovedi.
     * @return Mono emitting SecurityContextImpl if authorization was successful or Mono.empty().
     * otherwise.
     * Mono emitujúce SecurityContextImpl obsahujúce ak autorizácia prebehla úspešne,
     * Mono.empty() inak.
     */
    override fun load(p0: ServerWebExchange): Mono<SecurityContext> {
        val request = p0.request
        val authHeader: String? = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        if(authHeader!=null && authHeader.startsWith(SecurityConstants.TOKEN_PREFIX))
        {
            val auth = UsernamePasswordAuthenticationToken(authHeader,authHeader)
            return this.authenticationManager.authenticate(auth).map { a-> SecurityContextImpl(a) }
        }
        return Mono.empty()
    }
}