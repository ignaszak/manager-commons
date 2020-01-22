package net.ignaszak.manager.commons.error

interface ErrorCollector {
    fun add(error: Error)
    fun throwException()
}