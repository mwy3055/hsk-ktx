package date

import com.hsk.ktx.date.Date
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.*

class DateTest {
    data class TestData(
        val from: Date,
        val diff: Int,
        val expected: Date
    )

    private val daysTestData = listOf(
        TestData(Date(2022, 11, 30), 10, Date(2022, 12, 10)),
        TestData(Date(2022, 2, 1), 302, Date(2022, 11, 30)),
        TestData(Date(2022, 11, 30), 50, Date(2023, 1, 19)),
        TestData(Date(2022, 11, 30), 727, Date(2024, 11, 26)),
        TestData(Date(1469, 1, 23), 1676579, Date(6059, 5, 19)),
        TestData(Date(1216, 6, 15), 567631, Date(2770, 7, 30)),
        TestData(Date(8616, 3, 21), 138651, Date(8995, 10, 31)),
        TestData(Date(5351, 2, 15), 557049, Date(6876, 4, 9)),
        TestData(Date(1242, 10, 22), 1667191, Date(5807, 6, 4)),
        TestData(Date(817, 2, 12), 2397901, Date(7382, 5, 7)),
        TestData(Date(3254, 9, 23), 1561683, Date(7530, 6, 22)),
        TestData(Date(6778, 7, 12), 463099, Date(8046, 6, 13)),
        TestData(Date(6615, 3, 30), 799934, Date(8805, 5, 21)),
        TestData(Date(5047, 8, 1), 535615, Date(6514, 1, 17)),
        TestData(Date(6367, 1, 9), 256583, Date(7069, 7, 10)),
        TestData(Date(7170, 9, 28), 591206, Date(8789, 5, 29)),
        TestData(Date(6313, 7, 11), 1187432, Date(9564, 8, 8)),
        TestData(Date(3245, 7, 16), 635142, Date(4984, 7, 1)),
        TestData(Date(1832, 3, 5), 666897, Date(3658, 1, 28)),
        TestData(Date(244, 8, 23), 646527, Date(2014, 10, 10)),
        TestData(Date(9328, 7, 31), 170171, Date(9794, 6, 29)),
        TestData(Date(9932, 5, 29), 8358, Date(9955, 4, 17)),
        TestData(Date(3521, 1, 2), 790082, Date(5684, 3, 4)),
        TestData(Date(9760, 3, 18), 73087, Date(9960, 4, 26)),
    )

    @Test
    fun dayOfWeekTest() {
        repeat(10000) {
            val (year, month, day) = getRandomYmd()
            val localDate = LocalDate.of(year, month, day)
            val date = Date(year, month, day)
            assertThat(date.dayOfWeek.ordinal).isEqualTo(localDate.dayOfWeek.value % 7)
        }
    }

    private fun getRandomYmd(
        yearRange: IntRange = 1600..9999,
        monthRange: IntRange = 1..12,
        dayRange: IntRange = 1..31
    ): List<Int> {
        return Random().let {
            val year = it.nextInt(yearRange.first, yearRange.last)
            val month = it.nextInt(monthRange.first, monthRange.last)
            val day = it.nextInt(1, Date.maxDayOfMonth(year, month)).coerceIn(dayRange)
            listOf(year, month, day)
        }
    }

    @Test
    fun plusDaysTest() {
        daysTestData.forEach { (from, days, expected) ->
            assertThat(from.plusDays(days)).isEqualTo(expected)
        }
    }

    @Test
    fun minusDaysTest() {
        daysTestData.forEach { (expected, days, from) ->
            assertThat(from.minusDays(days)).isEqualTo(expected)
        }
    }

    @Test
    fun leapYearTest() {
        val years = listOf(
            1140 to true,
            8029 to false,
            558 to false,
            5245 to false,
            7336 to true,
            5879 to false,
            2066 to false,
            4854 to false,
            5135 to false,
            9740 to true,
            7352 to true,
            526 to false,
            1459 to false,
            5335 to false,
            5614 to false,
            6017 to false,
            382 to false,
            4357 to false,
            2331 to false,
            2109 to false,
        )
        years.forEach { (year, expected) ->
            assertThat(Date.isLeapYear(year)).isEqualTo(expected)
        }
    }

    @Test
    fun nowTest() {
        val now = LocalDate.now()
        assertThat(Date.now()).matches {
            it.year == now.year && it.month == now.monthValue && it.dayOfMonth == now.dayOfMonth
        }
    }

    @Test
    fun compareTest_smaller1() {
        assertIsLessThan(
            small = Date(2022, 12, 1),
            large = Date(2022, 12, 20)
        )
    }

    @Test
    fun compareTest_smaller2() {
        assertIsLessThan(
            small = Date(2022, 11, 20),
            large = Date(2022, 12, 20)
        )
    }

    @Test
    fun compareTest_smaller3() {
        assertIsLessThan(
            small = Date(2021, 10, 12),
            large = Date(2022, 2, 2)
        )
    }

    private fun assertIsLessThan(small: Date, large: Date) {
        assertThat(small).isLessThan(large)
    }

    private val plusMonthsTestData = listOf(
        TestData(Date(9522, 7, 4), 7, Date(9523, 2, 4)),
        TestData(Date(4898, 7, 26), 52, Date(4902, 11, 26)),
        TestData(Date(3665, 9, 18), 17, Date(3667, 2, 18)),
        TestData(Date(5608, 6, 10), 10, Date(5609, 4, 10)),
        TestData(Date(7997, 2, 17), 18, Date(7998, 8, 17)),
        TestData(Date(2585, 4, 11), 12, Date(2586, 4, 11)),
        TestData(Date(5802, 12, 24), 93, Date(5810, 9, 24)),
        TestData(Date(3384, 12, 26), 28, Date(3387, 4, 26)),
        TestData(Date(9393, 2, 5), 52, Date(9397, 6, 5)),
        TestData(Date(6179, 3, 16), 80, Date(6185, 11, 16)),
        TestData(Date(9482, 9, 1), 15, Date(9483, 12, 1)),
        TestData(Date(7792, 5, 6), 96, Date(7800, 5, 6)),
        TestData(Date(2360, 12, 12), 12, Date(2361, 12, 12)),
        TestData(Date(6567, 11, 8), 52, Date(6572, 3, 8)),
        TestData(Date(5107, 6, 1), 44, Date(5111, 2, 1)),
        TestData(Date(4066, 8, 14), 86, Date(4073, 10, 14)),
        TestData(Date(5258, 7, 25), 44, Date(5262, 3, 25)),
        TestData(Date(4332, 3, 3), 50, Date(4336, 5, 3)),
        TestData(Date(2051, 12, 29), 35, Date(2054, 11, 29)),
        TestData(Date(7417, 4, 24), 91, Date(7424, 11, 24)),
        TestData(Date(2022, 10, 31), 1, Date(2022, 11, 30))
    )

    @Test
    fun plusMonthsTest() {
        plusMonthsTestData.forEach { (from, months, expected) ->
            assertThat(from.plusMonths(months)).isEqualTo(expected)
        }
    }

    private val minusMonthsTestData = listOf(
        TestData(Date(9522, 7, 4), 7, Date(9523, 2, 4)),
        TestData(Date(4898, 7, 26), 52, Date(4902, 11, 26)),
        TestData(Date(3665, 9, 18), 17, Date(3667, 2, 18)),
        TestData(Date(5608, 6, 10), 10, Date(5609, 4, 10)),
        TestData(Date(7997, 2, 17), 18, Date(7998, 8, 17)),
        TestData(Date(2585, 4, 11), 12, Date(2586, 4, 11)),
        TestData(Date(5802, 12, 24), 93, Date(5810, 9, 24)),
        TestData(Date(3384, 12, 26), 28, Date(3387, 4, 26)),
        TestData(Date(9393, 2, 5), 52, Date(9397, 6, 5)),
        TestData(Date(6179, 3, 16), 80, Date(6185, 11, 16)),
        TestData(Date(9482, 9, 1), 15, Date(9483, 12, 1)),
        TestData(Date(7792, 5, 6), 96, Date(7800, 5, 6)),
        TestData(Date(2360, 12, 12), 12, Date(2361, 12, 12)),
        TestData(Date(6567, 11, 8), 52, Date(6572, 3, 8)),
        TestData(Date(5107, 6, 1), 44, Date(5111, 2, 1)),
        TestData(Date(4066, 8, 14), 86, Date(4073, 10, 14)),
        TestData(Date(5258, 7, 25), 44, Date(5262, 3, 25)),
        TestData(Date(4332, 3, 3), 50, Date(4336, 5, 3)),
        TestData(Date(2051, 12, 29), 35, Date(2054, 11, 29)),
        TestData(Date(7417, 4, 24), 91, Date(7424, 11, 24)),
        TestData(Date(2022, 10, 30), 1, Date(2022, 11, 30)),
        TestData(Date(2021, 12, 31), 3, Date(2022, 3, 31)),
        TestData(Date(2021, 12, 31), 15, Date(2023, 3, 31)),
    )

    @Test
    fun minusMonthsTest() {
        minusMonthsTestData.forEach { (expected, months, from) ->
            assertThat(from.minusMonths(months)).isEqualTo(expected)
        }
    }

    @Test
    fun toEpochSecondTest_fixed() {
        val (year, month, day) = listOf(1971, 1, 1)
        val date = Date(year, month, day)
        val localDate = LocalDate.of(year, month, day)
        assertThat(date.toEpochSecond()).isEqualTo(localDate.toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC))
    }

    @Test
    fun toEpochSecondTest_random() {
        repeat(10000) {
            val (year, month, day) = getRandomYmd(yearRange = 1970..2100)
            val date = Date(year, month, day)
            val localDate = LocalDate.of(year, month, day)
            println(localDate)
            assertThat(date.toEpochSecond())
                .isEqualTo(localDate.toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC))
        }
    }

    @Test
    fun ofEpochDayTest_random() {
        repeat(100000) {
            val epoch = Random().nextLong(10000000)
            val date = Date.ofEpochDay(epoch)
            val localDate = LocalDate.EPOCH.plus(epoch / Date.secondsOfDay, ChronoUnit.DAYS)
            assertThat(date).matches {
                it.year == localDate.year
                        && it.month == localDate.monthValue
                        && it.dayOfMonth == localDate.dayOfMonth
            }
        }
    }
}