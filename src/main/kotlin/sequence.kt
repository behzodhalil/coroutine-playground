fun main() {
    val list = listOf(1,2,3,4)

    list.asSequence()
        .filter {
            println("filter, ")
            it>0
        }.map {
            println("map, ")
        }.forEach { _ ->
            println("forEach, ")
        }
}