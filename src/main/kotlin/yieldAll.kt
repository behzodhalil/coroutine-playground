fun main() {
    example().forEach {
        println(it)
    }
}

private fun example() = sequence {
    yieldAll(1..5)
}