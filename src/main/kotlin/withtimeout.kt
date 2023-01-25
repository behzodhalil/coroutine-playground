import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() {
    example()
}

private fun example() = runBlocking {
    withTimeout(1500L) {
        repeat(3) { i ->
            println("$i Crunching numbers")
            delay(500L)
        }
    }
}