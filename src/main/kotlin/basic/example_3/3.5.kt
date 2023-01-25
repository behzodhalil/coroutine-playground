package basic.example_3

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    GlobalScope.launch {
        val producedResult = produceValue(this,"Hey")

        println(producedResult.await())
    }

    Thread.sleep(1000L)
}

fun <T> produceValue(scope: CoroutineScope, data: T):Deferred<T> {
    return scope.async {
        data
    }
}