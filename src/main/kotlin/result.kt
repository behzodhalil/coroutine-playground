import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    GlobalScope.launch {
        example("Behzod")
            .onSuccess { println(it) }
            .onFailure { println(it) }
    }

    Thread.sleep(1000L)
}

private suspend fun example(data: String): Result<String> {
    return withContext(Dispatchers.Default) {
        try {
            Result.success(data)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}