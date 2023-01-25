fun main() {
    val list = listOf(1,2,3)

    list.filter {
        println("filter, ")
        it>0
    }.map {
        println("map, ")
        it.toString()
    }.forEach { _ ->
        println("forEach, ")
    }

}