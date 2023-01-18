package basic.example_2

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * The difference between the default start mode and the "LAZY" mode
 * is that the default mode will start the coroutine immediately when it is launched and
 * the LAZY mode will start the coroutine only when its start method is invoked or if some other coroutine is waiting
 * for its completion with the join() method.
 */
@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
        println("Lazy job is started")
    }

    GlobalScope.launch {
        println("Default job is started")
        lazyJob.join()
        println("Lazy job is joined and printed")
    }

    Thread.sleep(1000L)
}