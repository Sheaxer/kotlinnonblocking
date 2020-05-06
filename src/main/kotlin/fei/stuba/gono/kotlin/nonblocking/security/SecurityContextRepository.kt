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

@Component
class SecurityContextRepository @Autowired constructor(
        private val authenticationManager: ReactiveAuthenticationManager): ServerSecurityContextRepository {


    override fun save(p0: ServerWebExchange?, p1: SecurityContext?): Mono<Void> {
        TODO("Not yet implemented")
    }

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