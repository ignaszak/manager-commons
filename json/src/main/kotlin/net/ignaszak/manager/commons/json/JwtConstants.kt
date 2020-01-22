package net.ignaszak.manager.commons.json

object JwtConstants {

    const val TOKEN_HEADER = "Authorization"
    const val TOKEN_PREFIX = "Bearer "
    const val TOKEN_TYPE = "JWT"
    const val TOKEN_ISSUER = "secure-api"
    const val TOKEN_AUDIENCE = "secure-app"

    const val TOKEN_PARAM_TYPE = "type"
    const val TOKEN_PARAM_ROLES = "roles"
}