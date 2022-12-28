package basic.example_1

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val range1to100 = (1..100)
    val sleepTimeMillis = 1000L

    range1to100.forEach {
        GlobalScope.launch {
            val threadName = Thread.currentThread().name
            println("$it printed on thread $threadName")
        }

    }
    Thread.sleep(sleepTimeMillis)
}