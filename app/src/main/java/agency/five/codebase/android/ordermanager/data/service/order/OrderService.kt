package agency.five.codebase.android.ordermanager.data.service.order

import agency.five.codebase.android.ordermanager.data.currentuser.UserData
import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface OrderService {
    /*ORDER SERVICE*/
    fun allActiveOrders(establishmentId: String): Flow<List<Order>>
    fun allCompletedOrders(establishmentId: String): Flow<List<Order>>
    fun orderItems(orderId: String): Flow<List<OrderItem>>
    suspend fun orderById(id: String): Result<Order>
    suspend fun addOrder(order: Order): Result<String>
    suspend fun addOrderItem(orderItem: OrderItem): Result<Unit>
    suspend fun updateOrder(order: Order): Result<Unit>
    suspend fun updateOrderItem(orderItem: OrderItem): Result<Unit>
    suspend fun confirmOrder(order: Order): Result<Unit>
    suspend fun completeOrder(currentUser: UserData, orderId: String): Result<Unit>
    suspend fun deleteOrder(orderId: String): Result<Unit>
    suspend fun deleteOrderItem(orderItemId: String): Result<Unit>

    /*NOT CONFIRMED ORDER SERVICE*/
    fun notConfirmedOrderedItems(): Flow<List<NotConfirmedOrderItem>>
    suspend fun addNotConfirmedOrderedItem(orderedItem: NotConfirmedOrderItem)
    suspend fun incrementNotCompletedOrderItemAmount(orderItemName: String)
    suspend fun subtractNotCompletedOrderItemAmount(orderItemId: Int)
}

