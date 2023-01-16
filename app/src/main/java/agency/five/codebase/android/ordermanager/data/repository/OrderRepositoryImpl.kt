package agency.five.codebase.android.ordermanager.data.repository

import agency.five.codebase.android.ordermanager.data.database.*
import agency.five.codebase.android.ordermanager.model.ActiveOrder
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.OrderedItem
import agency.five.codebase.android.ordermanager.model.OrderedItemInActiveOrder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class OrderRepositoryImpl(
    private val menuItemDao: MenuItemDao,
    private val orderedItemDao: OrderedItemDao,
    private val activeOrderDao: ActiveOrderDao,
    private val orderedItemInActiveOrderDao: OrderedItemInActiveOrderDao,
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

    override fun orderedItems(): Flow<List<OrderedItem>> = orderedItemDao.getOrderedItems().map {
        it.map { dbOrderedItem ->
            OrderedItem(
                id = dbOrderedItem.id.toInt(),
                name = dbOrderedItem.name,
                amount = dbOrderedItem.amount,
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1,
    )

    override fun activeOrders(): Flow<List<ActiveOrder>> = activeOrderDao.getActiveOrders().map {
        it.map { dbActiveOrder ->
            ActiveOrder(
                id = dbActiveOrder.id.toInt(),
                tableNumber = dbActiveOrder.tableNumber,
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1,
    )

    override fun orderedItemsInActiveOrder(activeOrderId: Int): Flow<List<OrderedItemInActiveOrder>> =
        orderedItemInActiveOrderDao.getOrderedItemsInActiveOrder(activeOrderId).map {
            it.map { dbOrderedItemInActiveOrder ->
                OrderedItemInActiveOrder(
                    id = dbOrderedItemInActiveOrder.id.toInt(),
                    name = dbOrderedItemInActiveOrder.name,
                    amount = dbOrderedItemInActiveOrder.amount,
                    activeOrderId = dbOrderedItemInActiveOrder.activeOrderId.toInt()
                )
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

    override suspend fun addOrderedItem(orderedItem: OrderedItem) {
        withContext(bgDispatcher) {
            orderedItemDao.insertOrderedItem(
                DbOrderedItem(
                    id = orderedItem.id.toLong(),
                    name = orderedItem.name,
                    amount = orderedItem.amount
                )
            )
        }
    }

    override suspend fun confirmOrder(tableNumber: String) {
        withContext(bgDispatcher) {
            val activeOrderId = activeOrderDao.insertActiveOrder(
                DbActiveOrder(
                    tableNumber = tableNumber
                )
            )
            orderedItems().map {
                it.forEach { orderedItem ->
                    orderedItemInActiveOrderDao.insertOrderedItemInActiveOrder(
                        DbOrderedItemInActiveOrder(
                            name = orderedItem.name,
                            amount = orderedItem.amount,
                            activeOrderId = activeOrderId,
                        )
                    )
                }
            }
            orderedItemDao.deleteAllOrderedItems()
        }
    }

    override suspend fun addOrderedItemAmount(orderedItemId: Int) {
        withContext(bgDispatcher) {
            orderedItemDao.addOrderedItemAmount(orderedItemId)
        }
    }

    override suspend fun subtractOrderedItemAmount(orderedItemId: Int) {
        withContext(bgDispatcher) {
            orderedItemDao.subtractOrderedItemAmount(orderedItemId)
            orderedItemDao.deleteOrderedItemIfNecessary(orderedItemId)
        }
    }

    override suspend fun completeOrder(activeOrderId: Int) {
        withContext(bgDispatcher) {
            orderedItemInActiveOrderDao.deleteOrderedItemsInActiveOrder(activeOrderId)
            activeOrderDao.deleteActiveOrder(activeOrderId)
        }
    }
}
