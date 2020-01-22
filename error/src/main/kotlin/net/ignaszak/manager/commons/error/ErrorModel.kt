package net.ignaszak.manager.commons.error

import java.util.*
import java.util.stream.Collectors

class ErrorModel {
    private val errors: Set<ErrorInfo>

    constructor(error: Error) {
        errors = setOf(toErrorInfo(error))
    }

    constructor(errors: Set<Error>) {
        this.errors = errors
            .stream()
            .map(this::toErrorInfo)
            .collect(Collectors.toCollection { TreeSet(Comparator.comparing(ErrorInfo::code)) })
    }

    private fun toErrorInfo(error: Error): ErrorInfo {
        return ErrorInfo(error.code, error.message, error.name)
    }

    data class ErrorInfo (val code: String, val message: String, val name: String)
}