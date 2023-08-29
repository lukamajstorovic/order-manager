package agency.five.codebase.android.ordermanager.data.room

import agency.five.codebase.android.ordermanager.data.room.dao.MenuItemDao
import agency.five.codebase.android.ordermanager.data.room.dao.NotConfirmedOrderItemDao
import agency.five.codebase.android.ordermanager.data.room.model.DbMenuItem
import agency.five.codebase.android.ordermanager.data.room.model.DbNotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.OrderItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class NotConfirmedOrderServiceImpl(
    private val menuItemDao: MenuItemDao,
    private val orderedItemDao: NotConfirmedOrderItemDao,
    private val bgDispatcher: CoroutineDispatcher,
) : NotConfirmedOrderService {
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

    override fun orderedItems(): Flow<List<DbNotConfirmedOrderItem>> =
        orderedItemDao.getOrderItems().map {
            it.map { dbOrderedItem ->
                DbNotConfirmedOrderItem(
                    id = dbOrderedItem.id,
                    name = dbOrderedItem.name,
                    amount = dbOrderedItem.amount,
                )
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

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

    override suspend fun addOrderedItem(orderedItem: DbNotConfirmedOrderItem) {
        withContext(bgDispatcher) {
            orderedItemDao.insertOrderItem(
                orderedItem
            )
        }
    }

    override suspend fun incrementOrderedItemAmount(orderedItemName: String) {
        withContext(bgDispatcher) {
            orderedItemDao.incrementOrderItemAmount(orderedItemName)
        }
    }

    override suspend fun subtractOrderedItemAmount(orderedItemId: Int) {
        withContext(bgDispatcher) {
            orderedItemDao.subtractOrderItemAmount(orderedItemId)
            orderedItemDao.deleteOrderItemIfNecessary(orderedItemId)
        }
    }

    override suspend fun deleteAllOrderItems(): Result<Int> {
        return withContext(bgDispatcher) {
            try {
                val deletedRowsCount = orderedItemDao.deleteAllOrderItems()
                if (deletedRowsCount > 0) {
                    println("DELETED $deletedRowsCount")
                    Result.success(deletedRowsCount)
                } else {
                    println("DELETED 0")
                    Result.failure(Exception("ROOM EXCEPTION ROW COUNT: $deletedRowsCount"))
                }
            } catch (exception: Exception) {
                println("Exception during deleteAllOrderItems: $exception")
                Result.failure(exception)
            }
        }
    }

}
