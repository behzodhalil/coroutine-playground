package section_04

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val executor = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    GlobalScope.launch(context = executor) {
        println(Thread.currentThread().name)
    }

    Thread.sleep(1000L)
}