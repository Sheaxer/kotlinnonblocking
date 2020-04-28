package fei.stuba.gono.kotlin.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*

@Component
class JwtUtils : Serializable {
    companion object{
        fun createJWT(username: String) : String
        {
           return JWT.create()
                    .withSubject(username)
                    .withExpiresAt(Date(System.currentTimeMillis() + SecurityConstants.EXPIRE_LENGTH))
                    .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY.toByteArray()))
        }

        fun getUserFromToken(token: String): String?
        {
            return try {
                JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY.toByteArray()))
                        .build()
                        .verify(token.replace(SecurityConstants.TOKEN_PREFIX,""))
                        .subject
            }catch (ex: com.auth0.jwt.exceptions.JWTDecodeException) {
                null
            }
        }
    }
}