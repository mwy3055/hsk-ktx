package com.hsk.ktx

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> getTypeToken() = object : TypeToken<T>() {}

inline fun <reified T> getTypeFromToken() = getTypeToken<T>().type

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, getTypeFromToken<T>())

inline fun <reified T> List<T>.toJson(): String {
    return Gson().toJson(this, getTypeFromToken<List<T>>())
}