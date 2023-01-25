import kotlinx.coroutines.*
import java.lang.Exception

/**
 *   Notes:
 *
 * - SupervisorJob is similar to a regular Job, the only difference being that the cancellation is propagated only downwards.
 *   Meaning that child coroutines that throw exceptions, won’t cancel their parent.
 */
fun main() {
    exampleWithSupervisorScope()
}

private fun exampleWithSupervisorScope() = runBlocking {
    supervisorScope {
        val async = async {
            println("Async is started")
            throw ArithmeticException()
        }

        try {
            async.await()
        } catch (e: Exception) {
            println("Caught $e")
        }
    }
}

private fun example() = runBlocking {
    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)) {
        val firstChild = launch {
            println("First child throwing an exception")
            throw ArithmeticException()
        }

        val secondChild = launch {
            println("First child is cancelled: ${firstChild.isCancelled}")
            try {
                delay(5000)
            } catch (e: CancellationException) {
                println("Second child cancelled because supervisor got cancelled.")
            }
        }

        firstChild.join()
        println("Second child is active: ${secondChild.isActive}")
        supervisor.cancel()
        secondChild.join()
    }
}