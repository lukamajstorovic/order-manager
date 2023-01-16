package agency.five.codebase.android.ordermanager.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActiveOrderDao {
    @Query("SELECT * FROM activeOrders")
    fun getActiveOrders(): Flow<List<DbActiveOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActiveOrder(activeOrder: DbActiveOrder): Long

    @Query("DELETE FROM activeOrders WHERE id=:id")
    fun deleteActiveOrder(id: Int)
}
