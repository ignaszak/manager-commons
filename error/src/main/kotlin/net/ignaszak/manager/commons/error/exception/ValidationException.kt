package net.ignaszak.manager.commons.error.exception

import net.ignaszak.manager.commons.error.Error
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ValidationException : ErrorException {
    constructor(error: Error) : super(error)
    constructor(errorSet: Set<Error>) : super(errorSet)
}