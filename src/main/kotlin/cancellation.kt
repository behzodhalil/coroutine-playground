import kotlinx.coroutines.*

/**
 *   Notes:
 *
 * - When you initiate multiple asynchronous operations that are dependent on each other,
 *   the possibilities of one failing, then leading to others also failing, increases.
 *
 * - When you cancel the parent coroutine, all of its children are recursively cancelled, too.
 *
 * - Coroutine code should be cooperative with cancellation. This means that the code you write in a suspending function
 *   should check if the coroutine is still active before doing any intensive work.
 *
 * - The coroutine stopped executing after you cancelled the job,
 *   because the isActive flag is set to false as soon as you cancel the coroutine.
 */

fun main() {
    exampleWithCancellation()
}

private fun exampleWithNoCooperativeAndCancellation() = runBlocking {
    val startTime = System.currentTimeMillis()

    val job = launch(context = Dispatchers.Default) {
        var nextTime = startTime
        var i = 0

        while(i<10) {
            if (System.currentTimeMillis()>nextTime) {
                println("Doing heavy work: $i")
                i++
                nextTime+=500L
            }
        }
    }

    delay(1000L)
    println("Cancelling coroutine")
    job.cancel()
    println("Main: now I can quit!")
}

private fun exampleWithCooperativeAndCancellation() = runBlocking {
    val startTime = System.currentTimeMillis()

    val job = launch(context = Dispatchers.Default) {
        var nextTime = startTime
        var i = 0

        while(i<10) {
            if (System.currentTimeMillis()>nextTime) {
                println("Doing heavy work: $i")
                i++
                nextTime+=500L
            }
        }
    }

    delay(1000L)
    println("Cancelling coroutine")
    job.cancel()
    println("Main: now I can quit!")
}

private fun exampleWithCancellation() = runBlocking {

    val job = launch(context = Dispatchers.Default) {
        var i = 0

        while(i<10) {
            println("Doing heavy work: $i")
            i++
            delay(500L)
        }
    }

    delay(1000L)
    println("Cancelling coroutine")
    job.cancel()
    println("Main: now I can quit!")
}