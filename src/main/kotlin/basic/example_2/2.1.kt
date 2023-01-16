package basic.example_2

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
fun main() {
   GlobalScope.launch {
       getUserSuspend("Behzod")
   }

    Thread.sleep(1000L)
}

suspend fun getUserSuspend(name: String) {
    delay(500L)
    println(name)
}
