package net.ignaszak.manager.commons.json

import com.google.gson.Gson

object JsonUtils {

    private val gson = Gson()

    @JvmStatic
    fun <T> toObject(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

    @JvmStatic
    fun toJson(obj: Any): String {
        return gson.toJson(obj)
    }
}