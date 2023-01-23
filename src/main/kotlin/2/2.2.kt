package `2`

import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch(context = Dispatchers.Main) {
        val stringVariable = getString()
        println(stringVariable)
    }
}

suspend fun getString(): String {
    return withContext(Dispatchers.Default) {
        "It is suspend function"
    }
}