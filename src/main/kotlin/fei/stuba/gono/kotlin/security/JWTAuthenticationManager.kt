package fei.stuba.gono.kotlin.security

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JWTAuthenticationManager:ReactiveAuthenticationManager {


    override fun authenticate(p0: Authentication): Mono<Authentication> {
        val token = p0.credentials.toString()
        val user  = JwtUtils.getUserFromToken(token)
        if(user!= null)
        {
            val auth: UsernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(user,null, mutableListOf())
            return Mono.just(auth)
        }
        return Mono.empty()
    }
}