fun main() {
    printFruits().forEach {
        println(it)
    }
}

private fun printFruits() = sequence {
    yield("Apple")
    yield("Peach")
    yield("Melon")
}