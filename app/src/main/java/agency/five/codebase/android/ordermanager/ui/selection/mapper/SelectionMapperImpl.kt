package agency.five.codebase.android.ordermanager.ui.selection.mapper

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
        )
    }
}
