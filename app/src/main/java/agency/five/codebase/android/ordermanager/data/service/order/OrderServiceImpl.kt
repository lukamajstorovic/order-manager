package agency.five.codebase.android.ordermanager.data.service.order

import agency.five.codebase.android.ordermanager.data.currentuser.UserData
import agency.five.codebase.android.ordermanager.data.firebase.repository.order.OrderRepository
import agency.five.codebase.android.ordermanager.data.room.repository.NotConfirmedOrderRepository
import agency.five.codebase.android.ordermanager.data.room.model.DbNotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.model.OrderItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val notConfirmedOrderRepository: NotConfirmedOrderRepository,
    private val bgDispatcher: CoroutineDispatcher,
) : OrderService {

    override fun orderItems(orderId: String): Flow<List<OrderItem>> =
        orderRepository.getOrderItems(orderId).map {
            it.map { dbOrderItem ->
                dbOrderItem.toOrderItem()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override fun allActiveOrders(establishmentId: String): Flow<List<Order>> =
        orderRepository.getAllActiveOrders(establishmentId).map {
            it.map { dbOrder ->
                dbOrder.toOrder()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override fun allCompletedOrders(establishmentId: String): Flow<List<Order>> =
        orderRepository.getAllCompletedOrders(establishmentId).map {
            it.map { dbOrder ->
                dbOrder.toOrder()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override suspend fun orderById(id: String): Result<Order> {
        return orderRepository.getOrderById(id).map { dbOrder ->
            dbOrder.toOrder()
        }
    }

    override suspend fun addOrder(order: Order): Result<String> {
        return orderRepository.addOrder(
            order.toDbOrder()
        )
    }

    override suspend fun addOrderItem(orderItem: OrderItem): Result<Unit> {
        return orderRepository.addOrderItem(
            orderItem.toDbOrderItem()
        )
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    private suspend fun processOrderItems(
        orderId: String,
        orderItems: List<DbNotConfirmedOrderItem>
    ) {
        val deferredResults = orderItems.map { orderItem ->
            val dbOrderItem = orderItem.toOrderItem(orderId = orderId).toDbOrderItem()
            scope.async {
                orderRepository.addOrderItem(dbOrderItem)
            }
        }

        // Await each individual deferred result
        deferredResults.forEach { deferred ->
            val result = deferred.await()
            result.fold(
                onSuccess = {
                    println("Order item added successfully")
                },
                onFailure = { exception ->
                    println("Failed to add order item: ${exception.message}")
                    throw exception
                }
            )
        }
    }

    override suspend fun confirmOrder(order: Order): Result<Unit> {
        try {
            println("Starting confirmOrder function")

            val orderId = orderRepository.addOrder(order.toDbOrder())
            println("Order added successfully with orderId: $orderId")

            val orderItems = notConfirmedOrderRepository.orderedItems()
            processOrderItems(orderId.getOrNull()!!, orderItems.firstOrNull()!!)

            println("All order items added successfully")
            notConfirmedOrderRepository.deleteAllOrderItems()

            println("Finished confirmOrder function")
            return Result.success(Unit)
        } catch (exception: Exception) {
            println("Caught exception: ${exception.message}")
            return Result.failure(exception)
        } finally {
            scope.cancel() // Cancel the CoroutineScope when done
        }
    }

    override suspend fun incrementNotCompletedOrderItemAmount(orderItemName: String) {
        withContext(bgDispatcher) {
            notConfirmedOrderRepository.incrementOrderedItemAmount(orderItemName)
        }
    }

    override suspend fun subtractNotCompletedOrderItemAmount(orderItemId: Int) {
        withContext(bgDispatcher) {
            notConfirmedOrderRepository.subtractOrderedItemAmount(orderItemId)
        }
    }

    override suspend fun updateOrder(order: Order): Result<Unit> {
        return orderRepository.updateOrder(
            order.toDbOrder()
        )
    }

    override suspend fun updateOrderItem(orderItem: OrderItem): Result<Unit> {
        return orderRepository.updateOrderItem(
            orderItem.toDbOrderItem()
        )
    }

    override suspend fun completeOrder(currentUser: UserData, orderId: String): Result<Unit> {
        return orderById(orderId).fold(
            onSuccess = { order ->
                orderRepository.updateOrder(
                    order.copy(
                        completeOrderStaffId = currentUser.id,
                        active = false
                    ).toDbOrder()
                )
            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )
    }

    override suspend fun deleteOrder(orderId: String): Result<Unit> {
        try {
            val orderItemsFlow = orderItems(orderId)
            orderItemsFlow.map { orderItems ->
                println(orderItems.toString())
                orderItems.forEach { orderItem ->
                    println(orderItem)
                    deleteOrderItem(orderItem.id).onFailure {
                        throw(it)
                    }
                }
            }
            orderRepository.deleteOrder(orderId)
        } catch (exception: Exception) {
            return Result.failure(exception)
        }
        return Result.success(Unit)
    }

    override suspend fun deleteOrderItem(orderItemId: String): Result<Unit> {
        return orderRepository.deleteOrderItem(orderItemId)
    }

    override fun notConfirmedOrderedItems(): Flow<List<NotConfirmedOrderItem>> =
        notConfirmedOrderRepository.orderedItems().map {
            it.map { dbNotConfirmedOrderItem ->
                dbNotConfirmedOrderItem.toNotConfirmedOrderItem()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override suspend fun addNotConfirmedOrderedItem(orderedItem: NotConfirmedOrderItem) {
        notConfirmedOrderRepository.addOrderedItem(orderedItem.toDbNotConfirmedOrderItem())
    }
}
