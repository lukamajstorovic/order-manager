package agency.five.codebase.android.ordermanager.data.firebase.repository.order

import agency.five.codebase.android.ordermanager.data.firebase.model.DbOrder
import agency.five.codebase.android.ordermanager.data.firebase.model.DbOrderItem
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getAllActiveOrders(establishmentId: String): Flow<List<DbOrder>>
    fun getAllCompletedOrders(establishmentId: String): Flow<List<DbOrder>>
    fun getOrderItems(orderId: String): Flow<List<DbOrderItem>>
    suspend fun getOrderById(id: String): Result<DbOrder>
    suspend fun addOrder(order: DbOrder): Result<String> // returns orderId
    suspend fun addOrderItem(orderItem: DbOrderItem): Result<Unit>
    suspend fun updateOrder(order: DbOrder): Result<Unit>
    suspend fun updateOrderItem(orderItem: DbOrderItem): Result<Unit>
    suspend fun deleteOrder(orderId: String): Result<Unit>
    suspend fun deleteOrderItem(orderItemId: String): Result<Unit>
}
