package agency.five.codebase.android.ordermanager.data.firebase

import agency.five.codebase.android.ordermanager.model.ActiveOrder
import agency.five.codebase.android.ordermanager.model.OrderedItemInActiveOrder
import kotlinx.coroutines.flow.Flow

interface ActiveOrderService {
    fun activeOrders(): Flow<List<ActiveOrder>>
    fun orderedItemsInActiveOrder(activeOrderId: Int): Result<Flow<List<OrderedItemInActiveOrder>>>
    suspend fun confirmOrder(tableNumber: String): Result<Unit>
    suspend fun completeOrder(activeOrderId: Int): Result<Unit>
}
