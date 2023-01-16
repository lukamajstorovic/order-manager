package agency.five.codebase.android.ordermanager.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderedItemDao {
    @Query("SELECT * FROM orderedItems")
    fun getOrderedItems(): Flow<List<DbOrderedItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrderedItem(orderedItem: DbOrderedItem): Long

    @Query("DELETE FROM orderedItems")
    fun deleteAllOrderedItems()

    @Query("UPDATE orderedItems SET amount=amount+1 WHERE id=:id")
    fun addOrderedItemAmount(id: Int)

    @Query("UPDATE orderedItems SET amount=amount-1 WHERE id=:id")
    fun subtractOrderedItemAmount(id: Int)

    @Query("DELETE FROM orderedItems WHERE id=:id AND amount=0")
    fun deleteOrderedItemIfNecessary(id: Int)
}
