package fei.stuba.gono.kotlin.nonblocking.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import fei.stuba.gono.kotlin.security.SecurityConstants
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*

@Component
class JwtUtils : Serializable {
    companion object{
        fun createJWT(username: String) : String
        {
           return SecurityConstants.TOKEN_PREFIX + JWT.create()
                    .withSubject(username)
                    .withExpiresAt(Date(System.currentTimeMillis() + SecurityConstants.EXPIRE_LENGTH))
                    .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY.toByteArray()))
        }

        fun getUserFromToken(token: String): String?
        {
            return try {
                val decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY.toByteArray()))
                        .build()
                        .verify(token.replace(SecurityConstants.TOKEN_PREFIX,""))
                if(Date().before(decodedJWT.expiresAt))
                    decodedJWT.subject
                else null
            }catch (ex: com.auth0.jwt.exceptions.JWTDecodeException) {
                null
            }
        }
    }
}