package agency.five.codebase.android.ordermanager.mock

import agency.five.codebase.android.ordermanager.model.MenuItem

object MenuItemMock {
    fun getMenuItemList(): List<MenuItem> = listOf(
        MenuItem(
            id = 1,
            name = "food",
            iconName = "food",
        ),
        MenuItem(
            id = 2,
            name = "drinks",
            iconName = "drinks",
        )
    )
}
