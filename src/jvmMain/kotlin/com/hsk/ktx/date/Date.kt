package com.hsk.ktx

import java.util.*

enum class DayOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
}

data class Date(
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
) : Comparable<Date> {
    val dayOfWeek: DayOfWeek

    init {
        // Zeller's Congruence
        val q = dayOfMonth
        val m = if (month <= 2) month + 12 else month
        val (k, j) = (if (month <= 2) year - 1 else year).let {
            listOf(it % 100, it / 100)
        }
        // h = 0: SATURDAY
        val h = (q + ((13 * (m + 1)) / 5) + k + (k / 4) + (j / 4) - 2 * j) % 7
        dayOfWeek = DayOfWeek.values()[(h + 6) % 7]
    }


    fun plusDays(days: Int): Date {
        var newYear = year
        var newMonth = month
        var newDay = dayOfMonth + days

        while (newDay > maxDayOfMonth(newYear, newMonth)) {
            newDay -= maxDayOfMonth(newYear, newMonth)
            newMonth++
            if (newMonth > 12) {
                newMonth -= 12
                newYear++
            }
        }
        return Date(newYear, newMonth, newDay)
    }

    fun minusDays(days: Int): Date {
        var newYear = year
        var newMonth = month
        var newDay = dayOfMonth - days
        while (newDay <= 0) {
            newDay += maxDayOfMonth(newYear, if (newMonth == 1) 12 else newMonth - 1)
            newMonth--
            if (newMonth <= 0) {
                newMonth = 12
                newYear--
            }
        }
        return Date(newYear, newMonth, newDay)
    }

    fun plusMonths(months: Int): Date {
        var newYear = year
        var newMonth = month + months
        var newDay = dayOfMonth
        while (newMonth > 12) {
            newYear++
            newMonth -= 12
        }
        if (newDay > maxDayOfMonth(newYear, newMonth)) {
            newDay = maxDayOfMonth(newYear, newMonth)
        }
        return Date(newYear, newMonth, newDay)
    }

    fun minusMonths(months: Int): Date {
        var newYear = year
        var newMonth = month - months
        var newDay = dayOfMonth
        while (newMonth <= 0) {
            newYear--
            newMonth += 12
        }
        if (newDay > maxDayOfMonth(newYear, newMonth)) {
            newDay = maxDayOfMonth(newYear, newMonth)
        }
        return Date(newYear, newMonth, newDay)
    }

    companion object {
        fun now(): Date {
            val t = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
            }
            val year = t.get(Calendar.YEAR)
            val month = t.get(Calendar.MONTH) + 1
            val day = t.get(Calendar.DAY_OF_MONTH)
            return Date(year, month, day)
        }

        fun isLeapYear(year: Int) = when {
            year % 4 == 0 && year % 100 != 0 -> true
            year % 400 == 0 -> true
            else -> false
        }

        fun maxDayOfMonth(year: Int, month: Int): Int {
            return if (month == 2) {
                if (isLeapYear(year)) 29 else 28
            } else {
                maxDayOfMonth[month]!!
            }
        }

        private val maxDayOfMonth = mapOf(
            1 to 31,
            3 to 31,
            4 to 30,
            5 to 31,
            6 to 30,
            7 to 31,
            8 to 31,
            9 to 30,
            10 to 31,
            11 to 30,
            12 to 31
        )

        val MAX = Date(9999, 12, 31)
        val MIN = Date(1, 1, 1)
    }

    override fun compareTo(other: Date): Int {
        return when {
            year != other.year -> year.compareTo(other.year)
            month != other.month -> month.compareTo(other.month)
            dayOfMonth != other.dayOfMonth -> dayOfMonth.compareTo(other.dayOfMonth)
            else -> 0
        }
    }
}