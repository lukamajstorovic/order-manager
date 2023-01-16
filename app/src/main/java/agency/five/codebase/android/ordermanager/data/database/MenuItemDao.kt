package agency.five.codebase.android.ordermanager.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menuItems")
    fun getMenuItems(): Flow<List<DbMenuItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenuItem(menuItem: DbMenuItem)

    @Query("DELETE FROM menuItems WHERE id=:id")
    fun deleteMenuItem(id: Int)
}
