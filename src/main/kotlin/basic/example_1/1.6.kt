package basic.example_1

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val jobA = GlobalScope.launch(start = CoroutineStart.LAZY) {
        delay(200L)
        println("it's job a")
        delay(200L)
    }

    GlobalScope.launch {
        delay(200L)
        println("it's job b")
        jobA.join()
        delay(200L)
        println("it's second job b")
    }

    Thread.sleep(1000L)
}