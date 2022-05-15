import org.junit.jupiter.api.Test
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
}