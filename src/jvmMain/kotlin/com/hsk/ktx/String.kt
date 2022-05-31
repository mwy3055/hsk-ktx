package com.hsk.ktx

fun String?.isAlphabet(): Boolean {
    if (isNullOrEmpty()) return false
    return all { it.isLowerCase() || it.isUpperCase() }
}