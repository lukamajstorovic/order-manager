package agency.five.codebase.android.ordermanager.ui.selection.mapper

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.ui.component.service.SelectionCardViewState
import agency.five.codebase.android.ordermanager.ui.selection.SelectionViewState

class SelectionMapperImpl : SelectionMapper {
    override fun toSelectionViewState(menuItems: List<MenuItem>): SelectionViewState {
        val selectionCardViewStateCollection = menuItems.map { mapMenuItem(it) }
        return SelectionViewState(
            selectionCardViewStateCollection = selectionCardViewStateCollection
        )
    }

    private fun mapMenuItem(menuItem: MenuItem): SelectionCardViewState {
        return SelectionCardViewState(
            id = menuItem.id,
            name = menuItem.name,
            iconId = getResourceId(menuItem.iconName),
            price = menuItem.price,
        )
    }

    private fun getResourceId(iconName: String): Int {
        return when (iconName) {
            "beer_0_33" -> {
                R.drawable.beer_0_33
            }
            "beer_0_5" -> {
                R.drawable.beer_0_5
            }
            "coca_cola_0_5" -> {
                R.drawable.coca_cola_0_5
            }
            "cocktail" -> {
                R.drawable.cocktail
            }
            else -> {
                R.drawable.wine_0_187
            }
        }
    }
}
