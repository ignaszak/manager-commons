package net.ignaszak.manager.commons.json.model

import net.ignaszak.manager.commons.model.user.UserModel

data class JwtModel(val userModel: UserModel, val roles: Set<String>)