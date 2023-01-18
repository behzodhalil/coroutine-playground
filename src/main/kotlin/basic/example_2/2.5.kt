package basic.example_2

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    var isOpenDoor = false

    println("Come and the open door")
    GlobalScope.launch {
        delay(3000L)

        isOpenDoor = true
    }

    GlobalScope.launch {
        repeat(4) {
            println("Trying to open the door...")
            delay(800L)

            if (isOpenDoor) {
                println("Open the door...")
            } else {
                println("The door still is locked...")
            }
        }

    }
    Thread.sleep(5000L)
}