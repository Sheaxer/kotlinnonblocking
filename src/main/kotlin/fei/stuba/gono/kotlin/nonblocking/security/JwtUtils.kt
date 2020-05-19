package fei.stuba.gono.kotlin.nonblocking.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import fei.stuba.gono.kotlin.security.SecurityConstants
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*
/***
 * Singleton object that implements the  JWT helper functions.
 * Singleton objekt implementujúci JWT pomocné funkcie.
 */
@Component
object JwtUtils : Serializable {
    /***
     * Creates JWT from given user name, with expiration date obtained from SecurityConstants class,
     * signed with the key from SecurityConstants class.
     * Vytvorí JWT pomocou zadaného používateľského mena, s expiračným dátumom získaným
     * z triedy SecurityConstants a podpísaný kľúčom získaným z triedy SecurityConstants.
     * @see fei.stuba.gono.kotlin.security.SecurityConstants
     * @param username user name to be added as subject to the JWT.
     *                 používateľské meno, ktoré sa má pridať ako subjekt JWT.
     * @return JWT
     */
        fun createJWT(username: String) : String
        {
           return SecurityConstants.TOKEN_PREFIX + JWT.create()
                    .withSubject(username)
                    .withExpiresAt(Date(System.currentTimeMillis() + SecurityConstants.EXPIRE_LENGTH))
                    .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY.toByteArray()))
        }
    /***
     * Retrieves the subject from JWT, if the JWT is valid and not expired.
     * Získa subjekt z JWT, ak je JWT validný a nebol expirovaný.
     * @param token JWT
     * @return user name that is the subject of the JWT, if JWT was valid, null otherwise.
     * používateľské meno ktoré je subjekt JWT, ak je JWT validný, inak null.
     */
        fun getUserFromToken(token: String): String?
        {
            return try {
                val decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY.toByteArray()))
                        .build()
                        // removes "Beaerer " prefix and verifies
                        .verify(token.replace(SecurityConstants.TOKEN_PREFIX,""))
                // if not expired
                if(Date().before(decodedJWT.expiresAt))
                    decodedJWT.subject
                else null
            }catch (ex: com.auth0.jwt.exceptions.JWTDecodeException) {
                null
            }
        }
}