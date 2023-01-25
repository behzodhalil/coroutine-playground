package `2`

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine


suspend fun <T : Any> getValue(provider: () -> T): T {
    return suspendCoroutine { continuation ->
        continuation.resumeWith(Result.runCatching { provider() })
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun executeBackground(action: suspend () -> Unit) {
    GlobalScope.launch { action() }
}

@OptIn(DelicateCoroutinesApi::class)
fun executeMain(action: suspend () -> Unit) {
    GlobalScope.launch(context = Dispatchers.Main) {
        action()
    }
}

fun main() {
    executeBackground {
        getValue {
            println("execute in the main thread")
        }
    }
    Thread.sleep(1000L)
}