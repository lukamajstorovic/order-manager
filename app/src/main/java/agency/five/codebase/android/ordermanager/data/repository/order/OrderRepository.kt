package agency.five.codebase.android.ordermanager.data.repository.order

import agency.five.codebase.android.ordermanager.data.currentuser.UserData
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    /*ORDER SERVICE*/
    fun allActiveOrders(establishmentId: String): Flow<List<Order>>
    fun allCompletedOrders(): Flow<List<Order>>
    fun orderItems(orderId: String): Flow<List<OrderItem>>
    suspend fun orderById(id: String): Result<Order>
    suspend fun addOrder(order: Order): Result<String>
    suspend fun addOrderItem(orderItem: OrderItem): Result<Unit>
    suspend fun incrementNotCompletedOrderItemAmount(orderItemName: String)
    suspend fun subtractNotCompletedOrderItemAmount(orderItemId: Int)
    suspend fun updateOrder(order: Order): Result<Unit>
    suspend fun updateOrderItem(orderItem: OrderItem): Result<Unit>
    suspend fun confirmOrder(order: Order): Result<Unit>
    suspend fun completeOrder(currentUser: UserData, orderId: String): Result<Unit>
    /*MENU ITEMS*/
    fun menuItems(): Flow<List<MenuItem>>
    suspend fun addMenuItem(menuItem: MenuItem)
    suspend fun removeMenuItem(menuItemId: Int)
    /*NOT CONFIRMED ORDER SERVICE*/
    fun notConfirmedOrderedItems(): Flow<List<NotConfirmedOrderItem>>
    suspend fun addNotConfirmedOrderedItem(orderedItem: NotConfirmedOrderItem)
}

