import kotlinx.coroutines.*

/**
 *   Notes:
 *
 * - When async is used as a root coroutine builder,
 *   exceptions are thrown only when you call await().
 *
 * - CoroutineExceptionHandler catches only exceptions that
 *   were not handled in any other way, i.e. uncaught exceptions.
 *
 * - CoroutineExceptionHandler is invoked only on exceptions
 *   which are not expected to be handled by the user, so registering it in async coroutine builder
 *   and the like of it has no effect.
 *
 * - The general rule is the first exception wins.
 */

fun main() {
    exampleWithAsync()
}

@OptIn(DelicateCoroutinesApi::class)
private fun exampleWithAsync() = runBlocking {
    var isAwait = false

    val deferredJob = GlobalScope.async {

        println("Await is waiting")
        throw ArithmeticException("Arithmetic Exception is occured")
    }

    isAwait = true

    if (isAwait) {
        try {
            deferredJob.await()
        } catch (e: Exception) {
            println("Caught $e")
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun exampleWithExceptionHandle() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught the $throwable")
    }

    val parentJob = GlobalScope.launch {
        throw AssertionError("My Custom Assertion Error!")
    }

    val deferred = GlobalScope.async(exceptionHandler) {
        // Nothing will be printed,
        // relying on user to call deferred.await()
        throw ArithmeticException()
    }

    joinAll(parentJob,deferred)

}
@OptIn(DelicateCoroutinesApi::class)
private fun example() = runBlocking {
    val parentJob = GlobalScope.launch {
        println("2. Exception created via launch coroutine")
        throw IndexOutOfBoundsException()
    }
    println("1. Before joined job")

    // join() method makes the coroutine suspend and wait for the work to complete
    parentJob.join()
    println("3. Joined failed job")

    val childJob = GlobalScope.async {
        println("4. Exception created via async coroutine")
        throw ArithmeticException()
    }

    // 5
    try {
        childJob.await()
        println("5. Unreachable, this statement is never executed")
    } catch (e: Exception) {
        println("6. Caught ${e.javaClass.simpleName}")
    }
}