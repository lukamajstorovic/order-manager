package agency.five.codebase.android.ordermanager.mock

import kotlinx.coroutines.flow.MutableStateFlow

object MenuItemsDBMock {
    val menuItemIds = MutableStateFlow(setOf(1,2))
    fun insert(menuItemId: Int){ menuItemIds.value += menuItemId }
    fun delete(menuItemId: Int){ menuItemIds.value -= menuItemId }
}
