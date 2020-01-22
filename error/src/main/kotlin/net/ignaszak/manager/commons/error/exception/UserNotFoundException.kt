package net.ignaszak.manager.commons.error.exception

import net.ignaszak.manager.commons.error.Error
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException : ErrorException(Error.USR_USER_NOT_FOUND)