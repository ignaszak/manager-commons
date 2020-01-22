package net.ignaszak.manager.commons.error

import net.ignaszak.manager.commons.error.exception.ValidationException
import java.util.*

class ErrorCollectorImpl : ErrorCollector {
    private val errorSet: MutableSet<Error>

    init {
        errorSet = TreeSet(Comparator.comparing(Error::code))
    }

    override fun add(error: Error) {
        errorSet.add(error)
    }

    override fun throwException() {
        if (errorSet.isNotEmpty()) {
            throw ValidationException(errorSet)
        }
    }


}