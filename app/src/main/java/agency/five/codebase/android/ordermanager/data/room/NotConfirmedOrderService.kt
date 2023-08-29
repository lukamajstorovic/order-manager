package agency.five.codebase.android.ordermanager.data.room

import agency.five.codebase.android.ordermanager.data.room.model.DbNotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface NotConfirmedOrderService {
    fun menuItems(): Flow<List<MenuItem>>
    fun orderedItems(): Flow<List<DbNotConfirmedOrderItem>>
    suspend fun addMenuItem(menuItem: MenuItem)
    suspend fun removeMenuItem(menuItemId: Int)
    suspend fun addOrderedItem(orderedItem: DbNotConfirmedOrderItem)
    suspend fun incrementOrderedItemAmount(orderedItemName: String)
    suspend fun subtractOrderedItemAmount(orderedItemId: Int)
    suspend fun deleteAllOrderItems()
}
