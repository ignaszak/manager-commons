package net.ignaszak.manager.commons.json

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import net.ignaszak.manager.commons.json.JwtConstants.TOKEN_AUDIENCE
import net.ignaszak.manager.commons.json.JwtConstants.TOKEN_ISSUER
import net.ignaszak.manager.commons.json.JwtConstants.TOKEN_PARAM_ROLES
import net.ignaszak.manager.commons.json.JwtConstants.TOKEN_PARAM_TYPE
import net.ignaszak.manager.commons.json.JwtConstants.TOKEN_PREFIX
import net.ignaszak.manager.commons.json.JwtConstants.TOKEN_TYPE
import net.ignaszak.manager.commons.json.exception.JwtException
import net.ignaszak.manager.commons.json.model.JwtModel
import net.ignaszak.manager.commons.model.user.UserModel
import java.nio.charset.Charset
import java.util.*
import java.util.stream.Collectors

object JwtUtils {

    @JvmStatic
    fun buildToken(userModel: UserModel, roles: Set<String>, jwtExpirationMs: Long, jwtSecret: String): String {
        return TOKEN_PREFIX + Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(toByteArray(jwtSecret)), SignatureAlgorithm.HS512)
            .setHeaderParam(TOKEN_PARAM_TYPE, TOKEN_TYPE)
            .setIssuer(TOKEN_ISSUER)
            .setAudience(TOKEN_AUDIENCE)
            .setSubject(JsonUtils.toJson(userModel))
            .setExpiration(Date(System.currentTimeMillis() + jwtExpirationMs))
            .claim(TOKEN_PARAM_ROLES, roles)
            .compact();
    }

    @JvmStatic
    fun getJwtModel(token: String, jwtSecret: String): JwtModel {
        if (!validTokenString(token)) throw JwtException("Invalid authentication token")

        try {
            val parsedToken = Jwts.parser()
                .setSigningKey(toByteArray(jwtSecret))
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))

            val userModelJson = parsedToken.body.subject

            return JwtModel(
                JsonUtils.toObject(userModelJson, UserModel::class.java),
                toRoleSet(parsedToken)
            )

        } catch (e: ExpiredJwtException) {
            throw JwtException("Request to parse expired JWT", e)
        } catch (e: UnsupportedJwtException) {
            throw JwtException("Request to parse unsupported JWT", e)
        } catch (e: MalformedJwtException) {
            throw JwtException("Request to parse invalid JWT", e)
        } catch (e: IllegalArgumentException) {
            throw JwtException("Request to parse empty or null JWT", e)
        }
    }

    @JvmStatic
    fun validTokenString(token: String): Boolean {
        return token.isNotBlank() && token.startsWith(TOKEN_PREFIX)
    }

    @JvmStatic
    private fun toByteArray(value: String): ByteArray {
        return value.toByteArray(Charset.forName("utf-8"))
    }

    @JvmStatic
    private fun toRoleSet(parsedToken: Jws<Claims>): Set<String> {
        val roles = parsedToken.body[TOKEN_PARAM_ROLES] as Set<*>
        return roles.stream()
            .map { role: Any? -> role!!.toString() }
            .collect(Collectors.toSet())
    }
}