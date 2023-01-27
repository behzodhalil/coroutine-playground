import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class, FlowPreview::class)
fun main() {
    val flowString = flow {
        for (num in 1..10) {
            if (num==5) {
                kotlinx.coroutines.delay(120L)
                emit("After delay emitted the value $num")
            } else {
                emit("Emitting the value $num")
            }

        }
    }

    GlobalScope.launch {
        flowString.debounce(100L).collect {
            println(it)
        }
    }

    Thread.sleep(1000L)
}