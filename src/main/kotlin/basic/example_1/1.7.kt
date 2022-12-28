package basic.example_1

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    with(GlobalScope) {
        val parentJob = launch {
            delay(200L)
            println("I'm a parent job")
            delay(200L)
        }

        launch(context = parentJob) {
            delay(200L)
            println("I'm child job")
            delay(100L)
        }

        val parentJob2 = launch {
            delay(200L)
            println("I'm a parent job 2")
            delay(200L)
        }

        if (parentJob.children.iterator().hasNext()) {
            println("The Job has children!")
        } else {
            println("The Job has NO children")
        }

        if (parentJob2.children.iterator().hasNext()) {
            println("The Job2 has children!")
        } else {
            println("The Job2 has NO children")
        }

        Thread.sleep(2000L)
    }
}