package net.ignaszak.manager.commons.error

import spock.lang.Specification

class ErrorSpec extends Specification {

    def "test toString method"() {
        given:
        def error = Error.UNKNOWN

        when:
        def message = error.toString()

        then:
        message == "0: Unknown error!"
    }
}
