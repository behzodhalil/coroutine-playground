package basic.example_2

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun main() {
    val users = listOf(
        UserForMeasurement(name = "Behzod"),
        UserForMeasurement(name = "Abdurahmon"),
        UserForMeasurement(name = "Oybek"),
        UserForMeasurement(name = "Abduqodir")
    )

    var user: UserForMeasurement

    val firstDurationResult = measureTimedValue  {
        for (i in users.indices) {
            user = users[i]
            println("1: Gets user: $user")
        }
    }

    // it's faster than others
    // 78.33us
    val secondDurationResult = measureTimedValue {
        for (i in users.indices) {
            // it shows the important of scope
            val user2 = users[i]
            println("2: Gets users: $user2")
        }
    }

    // it's pretty approach
    // 124.785us
    val thirdDurationResult = measureTimedValue {
        for ((index, item) in users.withIndex()) {
            println("3: Gets users: $item")
        }
    }

    println(firstDurationResult.duration)
    println(secondDurationResult.duration)
    println(thirdDurationResult.duration)
}

private data class UserForMeasurement(
    val name: String
)