package agency.five.codebase.android.ordermanager.mock

import agency.five.codebase.android.ordermanager.model.User

object UserMock {
    fun getUserRole(password: String): String {
        val users: List<User> = getUsers()
        for (user in users) {
            if (password == user.password) {
                return user.role
            }
        }
        return "denied"
    }
    private fun getUsers(): List<User> = listOf(
        User(
            role = "waiter",
            password = "1234"
        ),
        User(
            role = "cook",
            password = "4321"
        )
    )
}
