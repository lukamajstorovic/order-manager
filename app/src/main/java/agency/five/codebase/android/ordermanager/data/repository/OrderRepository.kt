package agency.five.codebase.android.ordermanager.data.repository

import agency.five.codebase.android.ordermanager.model.ActiveOrder
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.OrderedItem
import agency.five.codebase.android.ordermanager.model.OrderedItemInActiveOrder
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun menuItems(): Flow<List<MenuItem>>
    fun orderedItems(): Flow<List<OrderedItem>>
    fun activeOrders(): Flow<List<ActiveOrder>>
    fun orderedItemsInActiveOrder(activeOrderId: Int): Flow<List<OrderedItemInActiveOrder>>
    suspend fun addMenuItem(menuItem: MenuItem)
    suspend fun removeMenuItem(menuItemId: Int)
    suspend fun addOrderedItem(orderedItem: OrderedItem)
    suspend fun addOrderedItemAmount(orderedItemId: Int)
    suspend fun subtractOrderedItemAmount(orderedItemId: Int)
    suspend fun confirmOrder(tableNumber: String)
    suspend fun completeOrder(activeOrderId: Int)
}
