import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    /*val flowString = flow {
        for (i in 1..10) {
            GlobalScope.launch {
                emit("Emit the value $i")
            }
        }
    }

    GlobalScope.launch {
        flowString.collect {
            println(it)
        }
    }*/

    val flow  = channelFlow {
        for (i in 1..10) {
            GlobalScope.launch {
                trySend("Sent the value $i")
            }
        }
    }

    GlobalScope.launch {
        flow.collect {
            println(it)
        }
    }
    Thread.sleep(1000L)
}