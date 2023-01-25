package basic.example_3

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CustomScope: CoroutineScope {

    private var parentJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    fun start() {
        parentJob = Job()
    }

    fun stop() {
        parentJob.cancel()
    }
}
fun main() {
    val scope = CustomScope()

    scope.launch {
        println("Custom scope is launched")
    }

    scope.stop()
}