package agency.five.codebase.android.ordermanager.mock

import kotlinx.coroutines.flow.MutableStateFlow

object ActiveOrdersDBMock {
    val activeOrderIds = MutableStateFlow(setOf(1,2))
    fun insert(activeOrderId: Int){ activeOrderIds.value += activeOrderId}
    fun delete(activeOrderId: Int){ activeOrderIds.value -= activeOrderId}
}
