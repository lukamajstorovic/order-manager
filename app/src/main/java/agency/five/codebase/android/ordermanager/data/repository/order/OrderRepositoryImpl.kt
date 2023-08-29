package agency.five.codebase.android.ordermanager.data.repository.order

import agency.five.codebase.android.ordermanager.data.firebase.OrderService
import agency.five.codebase.android.ordermanager.data.room.NotConfirmedOrderService
import agency.five.codebase.android.ordermanager.data.room.dao.MenuItemDao
import agency.five.codebase.android.ordermanager.data.room.model.DbMenuItem
import agency.five.codebase.android.ordermanager.data.room.model.DbNotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.MenuItem
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderRepositoryImpl(
    private val menuItemDao: MenuItemDao,
    private val orderService: OrderService,
    private val notConfirmedOrderService: NotConfirmedOrderService,
    private val bgDispatcher: CoroutineDispatcher,
) : OrderRepository {
    override fun menuItems(): Flow<List<MenuItem>> = menuItemDao.getMenuItems().map {
        it.map { dbMenuItem ->
            MenuItem(
                id = dbMenuItem.id,
                name = dbMenuItem.name,
                iconName = dbMenuItem.iconName,
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1,
    )

    override fun orderItems(orderId: String): Flow<List<OrderItem>> =
        orderService.getOrderItems(orderId).map {
            it.map { dbOrderItem ->
                dbOrderItem.toOrderItem()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override fun allActiveOrders(): Flow<List<Order>> =
        orderService.getAllActiveOrders().map {
            it.map { dbOrder ->
                dbOrder.toOrder()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override fun allCompletedOrders(): Flow<List<Order>> =
        orderService.getAllCompletedOrders().map {
            it.map { dbOrder ->
                dbOrder.toOrder()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override suspend fun orderById(id: String): Result<Order> {
        return orderService.getOrderById(id).map { dbOrder ->
            dbOrder.toOrder()
        }
    }

    override suspend fun addMenuItem(menuItem: MenuItem) {
        withContext(bgDispatcher) {
            menuItemDao.insertMenuItem(
                DbMenuItem(
                    id = menuItem.id,
                    name = menuItem.name,
                    iconName = menuItem.iconName
                )
            )
        }
    }

    override suspend fun removeMenuItem(menuItemId: Int) {
        withContext(bgDispatcher) {
            menuItemDao.deleteMenuItem(id = menuItemId)
        }
    }

    override suspend fun addOrder(order: Order): Result<String> {
        return orderService.addOrder(
            order.toDbOrder()
        )
    }

    override suspend fun addOrderItem(orderItem: OrderItem): Result<Unit> {
        return orderService.addOrderItem(
            orderItem.toDbOrderItem()
        )
    }

    /*override suspend fun confirmOrder(order: Order): Result<Unit> {
         try {
             orderService.addOrder(
                 order.toDbOrder()
             ).fold(
                 onSuccess = { orderId ->
                     notConfirmedOrderService.orderedItems()
                         .collect {
                             it.forEach { orderItem ->
                                 orderService.addOrderItem(
                                     orderItem.toOrderItem(orderId = orderId).toDbOrderItem()
                                 ).fold(
                                     onSuccess = {println("SUCCEEDED")},
                                     onFailure = { exception ->
                                         println("THROWING EXCEPTION!!")
                                         throw (exception)
                                     }
                                 )
                                 println(orderItem.name)
                             }
                             println("BIIN")
                         }
                 },
                 onFailure = { exception ->
                     println("FAILED? WHAT")
                     return Result.failure(exception)
                 }
             )
             println("DOING~~~~~")
             notConfirmedOrderService.deleteAllOrderItems().fold(
                 onSuccess = {
                     println("~~~~~SUCCESS")
                     return Result.success(Unit)
                 },
                 onFailure = {
                     println("~~~~~FAIL")
                     return Result.failure(it)
                 }
             )
             println("FINISHED~~~~~")
         } catch (exception: Exception) {
             println("CAUGHT EXCEPTION~~~~~")
             return Result.failure(exception)
         }
         println("FINISHED~~~~~")
         return Result.success(Unit)
     }*/

    private val scope = CoroutineScope(Dispatchers.IO) // Create your own CoroutineScope

    private suspend fun processOrderItems(orderId: String, orderItems: List<DbNotConfirmedOrderItem>) {
        val deferredResults = orderItems.map { orderItem ->
            val dbOrderItem = orderItem.toOrderItem(orderId = orderId).toDbOrderItem()
            scope.async {
                orderService.addOrderItem(dbOrderItem)
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

            val orderId = orderService.addOrder(order.toDbOrder())
            println("Order added successfully with orderId: $orderId")

            val orderItems = notConfirmedOrderService.orderedItems()
            processOrderItems(orderId.getOrNull()!!, orderItems.firstOrNull()!!)

            println("All order items added successfully")
            notConfirmedOrderService.deleteAllOrderItems()

            println("Finished confirmOrder function")
            return Result.success(Unit)
        } catch (exception: Exception) {
            println("Caught exception: ${exception.message}")
            return Result.failure(exception)
        } finally {
            scope.cancel() // Cancel the CoroutineScope when done
        }
    }

    /*override suspend fun confirmOrder(order: Order): Result<Unit> {
        try {
            orderService.addOrder(
                order.toDbOrder()
            ).fold(
                onSuccess = { orderId ->
                    CoroutineScope(Dispatchers.IO).launch {
                        notConfirmedOrderService.orderedItems()
                            .map {
                                it.forEach { orderItem ->
                                    orderService.addOrderItem(
                                        orderItem.toOrderItem(orderId = orderId).toDbOrderItem()
                                    ).fold(
                                        onSuccess = { println("SUCCEEDED") },
                                        onFailure = { exception ->
                                            println("THROWING EXCEPTION!!")
                                            throw (exception)
                                        }
                                    )
                                    println(orderItem.name)
                                }
                                println("BIIN")
                            }
                        println("STILL IN SCOPE")
                    }
                    println("OUT")
                },
                onFailure = { exception ->
                    println("FAILED? WHAT")
                    return Result.failure(exception)
                }
            )
            println("DOING~~~~~")
            notConfirmedOrderService.deleteAllOrderItems().fold(
                onSuccess = {
                    println("~~~~~SUCCESS")
                    return Result.success(Unit)
                },
                onFailure = {
                    println("~~~~~FAIL")
                    return Result.failure(it)
                }
            )
            println("FINISHED~~~~~")
        } catch (exception: Exception) {
            println("CAUGHT EXCEPTION~~~~~")
            return Result.failure(exception)
        }
        println("FINISHED~~~~~")
        return Result.success(Unit)
    }*/

    override suspend fun incrementNotCompletedOrderItemAmount(orderItemName: String) {
        withContext(bgDispatcher) {
            notConfirmedOrderService.incrementOrderedItemAmount(orderItemName)
        }
    }

    override suspend fun subtractNotCompletedOrderItemAmount(orderItemId: Int) {
        withContext(bgDispatcher) {
            notConfirmedOrderService.subtractOrderedItemAmount(orderItemId)
        }
    }

    override suspend fun updateOrder(order: Order): Result<Unit> {
        return orderService.updateOrder(
            order.toDbOrder()
        )
    }

    override suspend fun updateOrderItem(orderItem: OrderItem): Result<Unit> {
        return orderService.updateOrderItem(
            orderItem.toDbOrderItem()
        )
    }

    override suspend fun completeOrder(orderId: String): Result<Unit> {
        return orderById(orderId).fold(
            onSuccess = { order ->
                orderService.updateOrder(
                    order.copy(active = false).toDbOrder()
                )
            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )
    }

    override fun notConfirmedOrderedItems(): Flow<List<NotConfirmedOrderItem>> =
        notConfirmedOrderService.orderedItems().map {
            it.map { dbNotConfirmedOrderItem ->
                dbNotConfirmedOrderItem.toNotConfirmedOrderItem()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override suspend fun addNotConfirmedOrderedItem(orderedItem: NotConfirmedOrderItem) {
        notConfirmedOrderService.addOrderedItem(orderedItem.toDbNotConfirmedOrderItem())
    }
}
