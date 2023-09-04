package agency.five.codebase.android.ordermanager.data.room

import agency.five.codebase.android.ordermanager.data.repository.menuItem.MenuItemRepository
import agency.five.codebase.android.ordermanager.data.room.dao.NotConfirmedOrderItemDao
import agency.five.codebase.android.ordermanager.data.room.model.DbNotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.MenuItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class NotConfirmedOrderServiceImpl(
    private val orderedItemDao: NotConfirmedOrderItemDao,
    private val bgDispatcher: CoroutineDispatcher,
) : NotConfirmedOrderService {

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
