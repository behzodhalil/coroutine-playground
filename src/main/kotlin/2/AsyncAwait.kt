package `2`

import basic.example_1.User
import kotlinx.coroutines.*

data class AsyncAwaitUser(
    val id: Int,
    val name: String
)

class AsyncAwait {
    suspend fun getUserFromNetwork(userId: Int): Deferred<AsyncAwaitUser> {
        return withContext(Dispatchers.IO) {
            Thread.sleep(3000L)
            println("CoroutineScope: ${Thread.currentThread().name}")
            async {
                AsyncAwaitUser(1,"Behzod")
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getUserFromNetwork2()  = GlobalScope.async {
        Thread.sleep(1000L)
        println("It is a coroutine scope")
        User(1,"behzod")
    }

}

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val result = AsyncAwait()
    GlobalScope.launch {
        result.getUserFromNetwork2().await()
    }
    Thread.sleep(2000L)
}