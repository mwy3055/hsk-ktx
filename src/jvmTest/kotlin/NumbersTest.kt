import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class NumbersTest {
    @Test
    fun testGcd_WithOne() {
        testGcd(1, 10, 1)
    }

    @Test
    fun testGcd_SameNumber() {
        testGcd(20, 20, 20)
    }

    @Test
    fun testGcd_PrimeNumbers() {
        testGcd(3, 7, 1)
    }

    @Test
    fun testGcd_NormalCase() {
        testGcd(25, 95, 5)
    }

    private fun testGcd(num1: Int, num2: Int, expected: Int) {
        val actual = num1.gcd(num2)
        assertEquals(expected, actual)
    }

    @Test
    fun equalsDelta_Equals() {
        val float = 0.3f
        val anotherFloat = (float + Delta / 10).toFloat()
        assert(float.equalsDelta(anotherFloat))
    }

    @Test
    fun equalsDelta_NotEquals() {
        val float = 0.3f
        val anotherFloat = (float + Delta).toFloat()
        assertFalse(float.equalsDelta(anotherFloat))
    }
}