package basic.example_3

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.NullPointerException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    GlobalScope.launch {
       val data = getData()
       println("Fetched data is $data")
    }

    Thread.sleep(1000L)
}

private suspend fun getData(): String {
    return suspendCoroutine { continuation ->
        val callback = object: Callback {
            override fun onResult(result: String) {
                continuation.resume(result)
            }

            override fun onError(e: Throwable) {
                continuation.resumeWithException(e)
            }
        }

        responseCallback(callback)
    }
}

private fun responseCallback(callback: Callback) {
    try {
        val result = "Success"
        callback.onResult(result)
    } catch (e: Exception) {
        callback.onError(e)
    }
}

interface Callback {
    fun onResult(result: String)
    fun onError(e: Throwable)
}