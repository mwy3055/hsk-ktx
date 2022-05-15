import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CollectionsTest {

    @Test
    fun collectionXor_RemoveElement() {
        val numbers = (1..10).toList()

        val expected = (1..9).toList()
        val actual = numbers.xor(10).toList()
        assertEquals(expected, actual)
    }

    @Test
    fun collectionXor_AddElement() {
        val numbers = (1..9).toList()

        val expected = (1..10).toList()
        val actual = numbers.xor(10).toList()
        assertEquals(expected, actual)
    }

    @Test
    fun collectionRandoms_NegativeSize() {
        var exceptionOccur = false

        val numbers = (1..100).toList()
        try {
            numbers.randoms(-1)
        } catch (e: IllegalArgumentException) {
            exceptionOccur = true
        }
        assert(exceptionOccur)
    }

    @Test
    fun collectionRandoms_ManyElements() {
        val numbers = (1..100).toList()

        val randomSize = 10
        val randoms = numbers.randoms(randomSize)
        assertEquals(randomSize, randoms.size)
        randoms.forEach { random -> assert(numbers.contains(random)) }
    }

    @Test
    fun collectionRandoms_FewElements() {
        val numbers = (1..5).toList()

        val randomSize = 10
        val randoms = numbers.randoms(randomSize)
        assertEquals(numbers, randoms)
    }

    @Test
    fun distinctRandoms_SameElements() {
        val manyOnes = (1..10).map { 1 }

        val actual = manyOnes.distinctRandoms(4)
        val expected = listOf(1)
        assertEquals(expected, actual)
    }

    @Test
    fun distinctRandoms_ReturnThis() {
        val numbers = (1..10).toList()
        val actual = numbers.distinctRandoms(100)
        assertEquals(numbers, actual)
    }

    @Test
    fun distinctRandoms_NormalCase() {
        val numbers = listOf(1, 1, 2, 3, 3, 4)
        val actual = numbers.distinctRandoms(3)

        val appeared = mutableSetOf<Int>()
        actual.forEach { random ->
            if (appeared.contains(random)) {
                assert(false) { "$random should be contained only once but: $actual" }
            }
            appeared.add(random)
        }
    }

    @Test
    fun collectionTruncate_SliceMiddle() {
        val numbers = (1..100).toMutableList()

        val truncateSize = 50
        val expected = (1..50).toList()
        val actual = numbers.truncate(truncateSize)
        numbers[11] = 1
        assertEquals(expected, actual)
    }

    @Test
    fun collectionTruncate_SliceOver() {
        val numbers = (1..100).toMutableList()

        val truncateSize = 1000
        val expected = (1..100).toList()
        val actual = numbers.truncate(truncateSize)
        numbers[11] = 1
        assertEquals(expected, actual)
    }

    @Test
    fun collectionTruncate_EmptyArray() {
        val emptyList = emptyList<Int>()

        val truncateSize = 10
        val actual = emptyList.truncate(truncateSize)
        assertEquals(emptyList, actual)
    }

    @Test
    fun collectionTruncate_NegativeSize() {
        var exceptionOccur = false

        val numbers = (1..10).toList()

        val truncateSize = -10
        try {
            numbers.truncate(truncateSize)
        } catch (e: IllegalArgumentException) {
            assertEquals("Size must be non-negative", e.message)
            exceptionOccur = true
        } finally {
            assert(exceptionOccur)
        }
    }

    private val iterableData: Iterable<String> = listOf("1", "2", "3")
    private val arrayData: Array<String> = arrayOf("1", "2", "3")
    val action = { s1: String, s2: String -> "${s1.length}, ${s2.length}" }

    @Test
    fun iterableZipForEach_WithIterable() {
        val expected = iterableData.zip(iterableData).map {
            action(it.first, it.second)
        }
        val actual = mutableListOf<String>()
        iterableData.zipForEach(iterableData) { s1, s2 ->
            actual.add(action(s1, s2))
        }
        assertEquals(expected, actual)
    }

    @Test
    fun iterableZipForEach_WithArray() {
        val expected = iterableData.zip(arrayData).map {
            action(it.first, it.second)
        }
        val actual = mutableListOf<String>()
        iterableData.zipForEach(arrayData) { s1, s2 ->
            actual.add(action(s1, s2))
        }
        assertEquals(expected, actual)
    }

    @Test
    fun arrayZipForEach_WithIterable() {
        val expected = arrayData.zip(iterableData).map {
            action(it.first, it.second)
        }
        val actual = mutableListOf<String>()
        arrayData.zipForEach(iterableData) { s1, s2 ->
            actual.add(action(s1, s2))
        }
        assertEquals(expected, actual)
    }

    @Test
    fun arrayZipForEach_WithArray() {
        val expected = arrayData.zip(arrayData).map {
            action(it.first, it.second)
        }
        val actual = mutableListOf<String>()
        arrayData.zipForEach(arrayData) { s1, s2 ->
            actual.add(action(s1, s2))
        }
        assertEquals(expected, actual)
    }

    @Test
    fun arrayRemoved_Exists() {
        val array = Array(10) { it }

        val expected = Array(9) { it }.toList()
        val actual = array.removed(9)
        assertEquals(expected, actual)
    }

    @Test
    fun arrayRemoved_NotExists() {
        val array = Array(10) { it }

        val expected = array.copyOf().toList()
        val actual = array.removed(11)
        assertEquals(expected, actual)
    }
}