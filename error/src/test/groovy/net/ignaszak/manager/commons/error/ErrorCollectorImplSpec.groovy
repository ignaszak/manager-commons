package net.ignaszak.manager.commons.error

import net.ignaszak.manager.commons.error.exception.ValidationException
import spock.lang.Specification

class ErrorCollectorImplSpec extends Specification {

    ErrorCollector errorCollector

    def setup() {
        this.errorCollector = new ErrorCollectorImpl()
    }

    def "test throw exception after single error is added"() {
        given:
        errorCollector.add(Error.UNKNOWN)

        when:
        errorCollector.throwException()

        then:
        def e = thrown ValidationException
        e.message == Error.UNKNOWN.toString()
    }

    def "test throw exception after many errors are added"() {
        given:
        errorCollector.add(Error.USR_USER_EXISTS)
        errorCollector.add(Error.UNKNOWN)

        when:
        errorCollector.throwException()

        then:
        def e = thrown ValidationException
        e.message == Error.UNKNOWN.toString() + ", " + Error.USR_USER_EXISTS.toString()
    }

    def "test throw exception with single error after duplicated errors added"() {
        given:
        errorCollector.add(Error.UNKNOWN)
        errorCollector.add(Error.UNKNOWN)

        when:
        errorCollector.throwException()

        then:
        def e = thrown ValidationException
        e.message == Error.UNKNOWN.toString()
    }

    def "do not throw exception when no error is added"() {
        when:
        errorCollector.throwException()

        then:
        notThrown ValidationException
    }
}
