package basic.example_1

data class User(
    val id: Int,
    val name: String
)

interface FakeRepository {
    fun fetchUser(): List<User>
}

class DefaultFakeRepository(
    private val users: List<User>
) : FakeRepository {

    override fun fetchUser(): List<User> {
        println("Fetching user")
        return users
    }
}

fun main() {
    val users = listOf<User>(
        User(1,"Behzod"),
        User(2,"Sardor")
    )
    val repository: FakeRepository = DefaultFakeRepository(users)
    println(repository.fetchUser())
}