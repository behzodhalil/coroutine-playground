package basic.example_3

import basic.example_1.User
import java.lang.Exception
import kotlin.concurrent.thread

fun main() {
    val random = (1..10).random()

    fetchDataSync(random) { response, failure ->
        response?.run {
            println(this)
        }
        failure?.printStackTrace()
    }
}

private fun fetchDataSync(
    id: Int,
    onResponse: (List<User>?, Throwable?) -> Unit
) {
    thread {
        try {
            Thread.sleep(1000L)
            val user = User(id, "Behzod")
            onResponse(listOf(user), null)
        } catch (e: Exception) {
            onResponse(null, e)
        }
    }
}