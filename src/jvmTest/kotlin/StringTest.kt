import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class StringTest {
    @Test
    fun testStringContainsOnlyAlphabet_True() {
        val string = "abcdef"
        assert(string.isAlphabet())
    }

    @Test
    fun testStringContainsOnlyAlphabet_Number() {
        val string = "abcd3f"
        assertFalse(string.isAlphabet())
    }

    @Test
    fun testStringContainsOnlyAlphabet_SpecialCharacter() {
        val string = "abcd#f"
        assertFalse(string.isAlphabet())
    }

    @Test
    fun testStringContainsOnlyAlphabet_Null() {
        val string: String? = null
        assertFalse(string.isAlphabet())
    }

    @Test
    fun testStringContainsOnlyAlphabet_Empty() {
        assertFalse("".isAlphabet())
    }
}