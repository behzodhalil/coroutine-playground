import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn

fun main() {
    example()
}

private fun example() {
    val coroutineScope = CoroutineScope(context = Dispatchers.Default)

    val sharedFlow = flow {
        emit(3)
        emit(2)
        emit(1)

        Thread.sleep(50L)
        coroutineScope.cancel()
    }.shareIn(coroutineScope, started = SharingStarted.Lazily)

    sharedFlow.onEach {
        println("Emit the $it")
    }.launchIn(coroutineScope)

    while (coroutineScope.isActive) {
    }

}