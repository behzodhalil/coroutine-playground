package basic.example_1

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val jobA = GlobalScope.launch(start = CoroutineStart.DEFAULT) {
        delay(200L)
        println("hey, it's me")
        delay(200L)
    }

    GlobalScope.launch {
        delay(200)
        println("Ping")
        jobA.join()
        println("Pong")
        delay(200)
    }

    Thread.sleep(1000L)
}