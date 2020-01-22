package net.ignaszak.manager.commons.error.exception

import net.ignaszak.manager.commons.error.Error
import java.util.stream.Collectors

abstract class ErrorException : RuntimeException {
    var errorSet: Set<Error>
        private set

    constructor(error: Error) {
        errorSet = setOf(error)
    }

    constructor(errorSet: Set<Error>) {
        this.errorSet = errorSet
    }

    override val message: String
        get() = errorSet.stream()
            .map { obj: Error -> obj.toString() }
            .collect(Collectors.joining(", "))
}