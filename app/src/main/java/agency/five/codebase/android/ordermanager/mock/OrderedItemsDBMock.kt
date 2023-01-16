package agency.five.codebase.android.ordermanager.mock

import kotlinx.coroutines.flow.MutableStateFlow

object OrderedItemsDBMock {
    val orderedItemIds = MutableStateFlow(setOf(1,2))
    fun insert(orderedItemId: Int){ orderedItemIds.value += orderedItemId }
    fun delete(orderedItemId: Int){ orderedItemIds.value -= orderedItemId }
}
