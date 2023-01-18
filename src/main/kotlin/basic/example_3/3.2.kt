package basic.example_3

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MyClass {
    private var counter = 0

    @Synchronized
    fun incrementCounter(value: Int) {
        counter += value
    }

    @Synchronized
    fun getCounter(): Int {
        return counter
    }

}

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val myClass = MyClass()
    GlobalScope.launch {
        doWork(myClass)
    }
    Thread.sleep(5000L)
}

private suspend fun doWork(myClass: MyClass) {
    coroutineScope {
        launch {
            for (i in 1..100) {
                myClass.incrementCounter(i)
            }
        }

        launch {
            for (i in 1..100) {
                myClass.incrementCounter(i)
            }
        }
    }
    println("Counter: ${myClass.getCounter()}")
}