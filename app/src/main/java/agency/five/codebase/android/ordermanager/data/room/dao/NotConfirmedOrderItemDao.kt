package agency.five.codebase.android.ordermanager.data.room.dao

import agency.five.codebase.android.ordermanager.data.room.model.DbNotConfirmedOrderItem
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotConfirmedOrderItemDao {
    @Query("SELECT * FROM orderItems")
    fun getOrderItems(): Flow<List<DbNotConfirmedOrderItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrderItem(orderItem: DbNotConfirmedOrderItem)

    @Query("DELETE FROM orderItems")
    fun deleteAllOrderItems()

    @Query("UPDATE orderItems SET amount=amount+1 WHERE name=:name")
    fun incrementOrderItemAmount(name: String)

    @Query("UPDATE orderItems SET amount=amount-1 WHERE id=:id")
    fun subtractOrderItemAmount(id: Int)

    @Query("DELETE FROM orderItems WHERE id=:id AND amount=0")
    fun deleteOrderItemIfNecessary(id: Int)
}
