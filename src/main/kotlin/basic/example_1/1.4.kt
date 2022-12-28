package basic.example_1

import kotlinx.coroutines.*

private const val MIN_TIME_MILLIS = 500L
private const val MAX_TIME_MILLIS = 1000L

@OptIn(DelicateCoroutinesApi::class)
fun main() {

    GlobalScope.launch {
        println("ya, it's running before delay")
        delay(MIN_TIME_MILLIS)
        println("yow, it's running after delay")
    }

    Thread.sleep(MAX_TIME_MILLIS)
}