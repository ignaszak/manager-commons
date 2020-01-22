package net.ignaszak.manager.commons.error

enum class Error(val code: String, val message: String) {
    UNKNOWN("0", "Unknown error!"),

    AUT_AUTHENTICATION("AUT-1", "Could not authenticate user!"),
    AUT_INVALID_TOKEN("AUT-2", "Invalid authentication token!"),

    USR_USER_EXISTS("USR-1", "User already exists!"),
    USR_INVALID_PASSWORD("USR-2", "Invalid password!"),
    USR_INVALID_EMAIL("USR-3", "Invalid email!"),
    USR_USER_NOT_FOUND("USR-4", "User not found!"),
    USR_BAD_CREDENTIALS("USR-5", "Bad credentials!");

    override fun toString(): String {
        return "$code: $message"
    }
}