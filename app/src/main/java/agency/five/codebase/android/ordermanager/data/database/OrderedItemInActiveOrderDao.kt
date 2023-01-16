package agency.five.codebase.android.ordermanager.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderedItemInActiveOrderDao {
    @Query("SELECT * FROM orderedItemsInActiveOrder WHERE activeOrderId=:activeOrderId")
    fun getOrderedItemsInActiveOrder(activeOrderId: Int): Flow<List<DbOrderedItemInActiveOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrderedItemInActiveOrder(orderedItemInActiveOrder: DbOrderedItemInActiveOrder): Long

    @Query("DELETE FROM orderedItemsInActiveOrder WHERE activeOrderId=:activeOrderId")
    fun deleteOrderedItemsInActiveOrder(activeOrderId: Int)
}
