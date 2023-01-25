fun main() {
    val sequence = generatorFib().take(7)

    sequence.forEach {
        println(it)
    }
}

private fun generatorFib() = sequence {
    yield(0L)
    var curr = 0L
    var next = 1L

    while (true) {
        yield(next)
        val tmp = curr+next
        curr = next
        next = tmp
    }
}