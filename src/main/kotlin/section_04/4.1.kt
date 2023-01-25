package section_04

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    GlobalScope.launch {
        val deferred = async {
            val result = getStrings()
            result
        }

        val deferredResult = deferred.await()
        println(deferredResult)
    }

    Thread.sleep(2000L)

}

private suspend fun getStrings(): List<String> {
    val strings = listOf("Behzod", "Behzod", "Behzod")
    delay(1000L)
    return strings
}