package basic.example_1

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    var isDoorOpen = false

    println("Unlock door, please wait....")

    GlobalScope.launch {
        delay(3000L)
        isDoorOpen = true
    }

    GlobalScope.launch {
        repeat(4) {
            println("Trying to open the door...")
            delay(800L)

            if (isDoorOpen) {
                println("Opened the door")
            } else {
                println("Closed the door")
            }
        }
    }

    Thread.sleep(5000L)
}