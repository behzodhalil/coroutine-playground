import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun main() {
    example()
}

@OptIn(DelicateCoroutinesApi::class)
private fun example() {

    /**
     *  val sharedFlow = MutableSharedFlow<Int>(replay = 2)
     */
    val sharedFlow = MutableSharedFlow<Int>(replay = 1)

    sharedFlow.tryEmit(5)
    sharedFlow.tryEmit(3)

    sharedFlow.onEach {
        println("Emitting: $it")
    }.launchIn(GlobalScope)

    sharedFlow.onEach {
        println("Caught $it")
    }.launchIn(GlobalScope)



    Thread.sleep(2000L)
}