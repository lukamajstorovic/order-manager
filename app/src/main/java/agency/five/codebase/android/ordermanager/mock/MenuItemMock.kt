package agency.five.codebase.android.ordermanager.mock

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.model.MenuItem
import androidx.compose.ui.res.stringResource

object MenuItemMock {
    fun getMenuItemList(): List<MenuItem> = listOf(
        MenuItem(
            id = 1,
            name = "Beer 0.33",
            iconName = "beer_0_33",
        ),
        MenuItem(
            id = 2,
            name = "Beer 0.5",
            iconName = "beer_0_5",
        ),
        MenuItem(
            id = 3,
            name = "Coca cola 0.5",
            iconName = "coca_cola_0_5",
        ),
        MenuItem(
            id = 4,
            name = "Cocktail",
            iconName = "cocktail",
        ),
        MenuItem(
            id = 5,
            name = "Wine 0.187",
            iconName = "wine_0_187",
        ),
    )
}
