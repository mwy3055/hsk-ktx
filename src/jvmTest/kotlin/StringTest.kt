import com.hsk.ktx.getDateString
import com.hsk.ktx.isAlphabet
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class StringTest {
    @Test
    fun isAlphabet_LowerCase() {
        val string = "abcdef"
        assert(string.isAlphabet())
    }

    @Test
    fun isAlphabet_UpperCase() {
        val string = "ABCDEF"
        assert(string.isAlphabet())
    }

    @Test
    fun isAlphabet_LowerAndUpperCase() {
        val string = "aBCdeF"
        assert(string.isAlphabet())
    }

    @Test
    fun isAlphabet_Number() {
        val string = "abcd3f"
        assertFalse(string.isAlphabet())
    }

    @Test
    fun isAlphabet_SpecialCharacter() {
        val string = "abcd#f"
        assertFalse(string.isAlphabet())
    }

    @Test
    fun isAlphabet_Null() {
        val string: String? = null
        assertFalse(string.isAlphabet())
    }

    @Test
    fun isAlphabet_Empty() {
        assertFalse("".isAlphabet())
    }

    @Test
    fun getDateString_YearMonth() {
        val result = getDateString(2022, 5)
        assertEquals("202205", result)
    }

    @Test
    fun getDateString_YearMonthDay_SingleDigit() {
        val result = getDateString(2022, 5, 2)
        assertEquals("20220502", result)
    }

    @Test
    fun getDateString_YearMonthDay_DoubleDigit() {
        val result = getDateString(2022, 10, 11)
        assertEquals("20221011", result)
    }
}