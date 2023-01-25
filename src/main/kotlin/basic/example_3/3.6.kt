package basic.example_3

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Coroutine Context can be thought of as a container that holds various elements
 * that are needed for a coroutine to run properly.
 */

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val defaultDispatcher = Dispatchers.Default

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Problem is $throwable")
    }

    val emptyJob = Job()

    val combinedContext = defaultDispatcher + exceptionHandler + emptyJob

    GlobalScope.launch(context = combinedContext) {
        println(Thread.currentThread().name)
    }

    Thread.sleep(1000L)

}