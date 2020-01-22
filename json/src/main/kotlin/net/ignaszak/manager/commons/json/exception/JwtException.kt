package net.ignaszak.manager.commons.json.exception

import java.lang.RuntimeException

class JwtException(message: String, cause: Throwable) : RuntimeException(message, cause)