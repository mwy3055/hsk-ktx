import com.hsk.ktx.Date
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class DateTest {
    data class TestData(
        val from: Date,
        val days: Int,
        val expected: Date
    )

    private val testDates = listOf(
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

    @Test
    fun test() {
        println(Date(596, 3, 10).dayOfWeek)
    }

    private fun getRandomYmd(): List<Int> {
        return Random().let {
            val year = it.nextInt(1600, 9999)
            val month = it.nextInt(1, 12)
            val day = it.nextInt(1, Date.maxDayOfMonth(year, month))
            listOf(year, month, day)
        }
    }

    @Test
    fun plusDaysTest() {
        testDates.forEach { (from, days, expected) ->
            assertThat(from.plusDays(days)).isEqualTo(expected)
        }
    }

    @Test
    fun minusDaysTest() {
        testDates.forEach { (expected, days, from) ->
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
}