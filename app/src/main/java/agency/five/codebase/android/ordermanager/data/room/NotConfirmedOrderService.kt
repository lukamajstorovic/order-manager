package agency.five.codebase.android.ordermanager.data.room

import agency.five.codebase.android.ordermanager.data.room.model.DbNotConfirmedOrderItem
import kotlinx.coroutines.flow.Flow

interface NotConfirmedOrderService {
    fun orderedItems(): Flow<List<DbNotConfirmedOrderItem>>
    suspend fun addOrderedItem(orderedItem: DbNotConfirmedOrderItem)
    suspend fun incrementOrderedItemAmount(orderedItemName: String)
    suspend fun subtractOrderedItemAmount(orderedItemId: Int)
    suspend fun deleteAllOrderItems(): Result<Int>
}
