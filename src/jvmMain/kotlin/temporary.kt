import kotlin.math.abs

fun String?.containsOnlyAlphabet(): Boolean {
    if (isNullOrEmpty()) return false
    return all { it.isLowerCase() || it.isUpperCase() || it == '%' }
}

fun <T> Array<T>.removed(value: T) = this.filter { it != value }

fun <T> Collection<T>.xor(element: T): Collection<T> {
    return if (this.contains(element)) {
        this.minus(element)
    } else {
        this.plus(element)
    }
}

fun <T> Collection<T>.randoms(size: Int): List<T> {
    if (this.size <= size) return this.toList()
    val elements = mutableListOf<T>()
    repeat(size) {
        elements.add(this.random())
    }
    return elements
}

fun <T> Collection<T>.distinctRandoms(size: Int): List<T> {
    val duplicateRemoved = distinct()
    if (duplicateRemoved.size <= size) return duplicateRemoved
    return duplicateRemoved.shuffled().take(size)
}

fun <T> Collection<T>.truncate(size: Int): List<T> {
    if (size < 0) throw IllegalArgumentException("Size must be non-negative")
    return if (this.size <= size) this.toList() else this.take(size)
}

fun Int.gcd(num: Int): Int {
    return if (this == 0) num else (num % this).gcd(this)
}

/**
 * Floating point equal comparison.
 * Uses [IBM formula](https://www.ibm.com/developerworks/java/library/j-jtp0114/#N10255), which is more robust
 * because taking a ratio of 2 numbers **cancels** out the effect of their scale relative to delta.
 */
fun Float.equalsDelta(other: Float) = abs(this / other - 1) < 1e-5

fun <T, R> Iterable<T>.zipForEach(other: Iterable<R>, block: (T, R) -> Unit) {
    this.zip(other).forEach { (thisInstance, otherInstance) -> block(thisInstance, otherInstance) }
}

fun <T, R> Array<out T>.zipForEach(other: Array<out R>, block: (T, R) -> Unit) {
    this.zip(other).forEach { (thisInstance, otherInstance) -> block(thisInstance, otherInstance) }
}