import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
}