fun <T> Collection<T>.xor(element: T): Collection<T> {
    return if (this.contains(element)) {
        this.minus(element)
    } else {
        this.plus(element)
    }
}

fun <T> Collection<T>.randoms(size: Int): List<T> {
    if (size < 0) throw IllegalArgumentException("Random size should be positive but given: $size")
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

fun <T, R> Iterable<T>.zipForEach(other: Iterable<R>, block: (T, R) -> Unit) {
    this.zip(other).forEach { (thisInstance, otherInstance) -> block(thisInstance, otherInstance) }
}

fun <T, R> Iterable<T>.zipForEach(other: Array<out R>, block: (T, R) -> Unit) {
    this.zip(other).forEach { (thisInstance, otherInstance) -> block(thisInstance, otherInstance) }
}

fun <T, R> Array<out T>.zipForEach(other: Iterable<R>, block: (T, R) -> Unit) {
    this.zip(other).forEach { (thisInstance, otherInstance) -> block(thisInstance, otherInstance) }
}

fun <T, R> Array<out T>.zipForEach(other: Array<out R>, block: (T, R) -> Unit) {
    this.zip(other).forEach { (thisInstance, otherInstance) -> block(thisInstance, otherInstance) }
}

fun <T> Array<T>.removed(value: T) = this.filter { it != value }