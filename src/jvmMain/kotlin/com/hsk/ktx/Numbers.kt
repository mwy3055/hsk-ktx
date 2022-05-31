package com.hsk.ktx

import kotlin.math.abs

fun Int.gcd(num: Int): Int {
    return if (this == 0) num else (num % this).gcd(this)
}

const val Delta = 1e-5

/**
 * Floating point equal comparison.
 * Uses [IBM formula](https://www.ibm.com/developerworks/java/library/j-jtp0114/#N10255), which is more robust
 * because taking a ratio of 2 numbers **cancels** out the effect of their scale relative to delta.
 */
fun Float.equalsDelta(other: Float) = abs(this / other - 1) < Delta