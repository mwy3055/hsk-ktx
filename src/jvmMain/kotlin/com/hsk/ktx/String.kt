package com.hsk.ktx

fun String?.isAlphabet(): Boolean {
    if (isNullOrEmpty()) return false
    return all { it.isLowerCase() || it.isUpperCase() }
}

fun getDateString(year: Int, month: Int, day: Int? = null): String = buildString {
    append(year)
    append(month.toString().padStart(2, '0'))
    day?.let { append(day.toString().padStart(2, '0')) }
}