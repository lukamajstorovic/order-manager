package agency.five.codebase.android.ordermanager.ui.selection.mapper

import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.ui.selection.SelectionViewState

interface SelectionMapper {
    fun toSelectionViewState(menuItems: List<MenuItem>): SelectionViewState
}
