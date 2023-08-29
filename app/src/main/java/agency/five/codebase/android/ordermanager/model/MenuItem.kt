package agency.five.codebase.android.ordermanager.model

data class MenuItem(
    val id: Int,
    val name: String,
    val iconName: String,
    val price: String = "1.50",
)
