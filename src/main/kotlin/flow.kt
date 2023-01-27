import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val flowString = flow {
        for (i in 1..100) {
            emit("Emitting the $i")
        }
    }
    GlobalScope.launch {
        flowString.collect {
            println(it)
        }
    }

    Thread.sleep(1000L)
}