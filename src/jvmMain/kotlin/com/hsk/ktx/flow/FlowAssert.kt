package com.hsk.ktx.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class FlowAssert<T> internal constructor(private val flow: Flow<T>) {

    suspend fun emits(predicate: (T) -> Boolean) {
        try {
            flow.first(predicate)
        } catch (e: NoSuchElementException) {
            throw FlowAssertionException("Expecting at least one element matches $predicate, but any element doesn't.")
        } catch (e: Exception) {
            throw FlowAssertionException("Exception while collecting: $e")
        }
    }

    suspend fun emits(value: T) {
        emits { it == value }
    }

}

fun <T> assertThat(flow: Flow<T>): FlowAssert<T> = FlowAssert(flow)