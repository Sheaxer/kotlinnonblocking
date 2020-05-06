package fei.stuba.gono.kotlin.nonblocking.security

import fei.stuba.gono.kotlin.nonblocking.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

@Component
class JWTAuthenticationManager @Autowired constructor(
        private val employeeService: EmployeeService
):ReactiveAuthenticationManager {


    override fun authenticate(p0: Authentication): Mono<Authentication> {
        val token = p0.credentials.toString()
        val user  = JwtUtils.getUserFromToken(token)
        if(user!= null)
        {
            return employeeService.employeeExistsByUsername(user).flatMap { b: Boolean ->
                if (b) {
                    val auth = UsernamePasswordAuthenticationToken(user, null, ArrayList())
                    // SecurityContextHolder.getContext().setAuthentication(auth);
                    return@flatMap Mono.just(auth)
                } else return@flatMap Mono.empty()
            }
        }
        return Mono.empty()
    }
}