package basic.example_3

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    fun context(): CoroutineContext
}

class CoroutineContextProviderImpl(
    private val context: CoroutineContext
) : CoroutineContextProvider {
    override fun context(): CoroutineContext {
        return context
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val parentJob = Job()

    val provider: CoroutineContextProvider = CoroutineContextProviderImpl(
        context = parentJob + Dispatchers.IO
    )

    GlobalScope.launch(context = provider.context()) {
        println(Thread.currentThread().name)
    }

    Thread.sleep(1000L)
}