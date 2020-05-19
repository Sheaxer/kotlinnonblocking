package fei.stuba.gono.kotlin.nonblocking.security

import fei.stuba.gono.kotlin.nonblocking.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

/***
 * Class implementing ReactiveAuthenticationManager interface.
 * Provides the authentication via validating a JWT.
 * Trieda implementujúca ReactiveAuthenticationManager rozhranie.
 * Poskytuje validáciu pomocou správneho JWT.
 * @see ReactiveAuthenticationManager
 * @see com.auth0.jwt.JWT
 */
@Component
class JWTAuthenticationManager @Autowired constructor(
        private val employeeService: EmployeeService
):ReactiveAuthenticationManager {

    /***
     * Validates if the credentials of Authentication object contains contain valid JWT with
     * a subject.
     * Validuje či credentials premenná objektu triedy Authentication obsahuje korektný JWT so
     * subjektom.
     * @param p0 authentication information
     *                       autentikačné informácie
     * @return authentication token with user name
     * autentifikačný token s používateľským menom.
     */
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
                } else return@flatMap Mono.empty<Authentication>()
            }
        }
        return Mono.empty()
    }
}