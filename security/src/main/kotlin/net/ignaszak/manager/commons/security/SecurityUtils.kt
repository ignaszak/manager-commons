package net.ignaszak.manager.commons.security

import net.ignaszak.manager.commons.error.Error
import net.ignaszak.manager.commons.error.exception.AuthException
import net.ignaszak.manager.commons.model.user.UserModel
import net.ignaszak.manager.commons.security.model.UserDetailsModel
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

object SecurityUtils {

    @JvmStatic
    fun toUserDetailsModel(authentication: Authentication): UserDetailsModel {
        val principal = authentication.principal

        if (principal !is UserDetailsModel) throw AuthException(Error.AUT_AUTHENTICATION)

        return principal
    }

    @JvmStatic
    fun getRolesSet(model: UserDetailsModel): Set<String> {
        return model.authorities
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet())
    }

    @JvmStatic
    fun getAuthentication(userModel: UserModel, roles: Set<String>): UsernamePasswordAuthenticationToken {
        val authorities = roles.stream()
            .map {role: String -> SimpleGrantedAuthority(role)}
            .collect(Collectors.toSet())
        return UsernamePasswordAuthenticationToken(userModel, authorities)
    }
}