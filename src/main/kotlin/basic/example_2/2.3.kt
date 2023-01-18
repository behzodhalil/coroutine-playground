package basic.example_2

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext


@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val items = listOf("Behzod", "Sardor", "Bobur")
    GlobalScope.launch {
        println(getNotes(items))
        println(getLastNote(items))
        println(takeTwoNotes(items))
    }
    Thread.sleep(2000L)
}


private suspend fun getNotes(data: List<String>): List<String> {
    delay(1000L)
    println("Coroutine context is $coroutineContext")
    return data.ifEmpty { emptyList() }
}

private suspend fun getLastNote(data: List<String>): List<String> {
    delay(500L)
    println("Coroutine context is $coroutineContext")
    return data.takeLast(1)
}

/**
 * The "take()" function returns the specified number of elements from the beginning of the list.
 * In this case, it returns the first two elements of the input list by passing the number 2 to the "take()" function.
 */
private suspend fun takeTwoNotes(data: List<String>): List<String> {
    return data.take(2)
}
