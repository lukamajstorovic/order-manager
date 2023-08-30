package agency.five.codebase.android.ordermanager.data.room

import agency.five.codebase.android.ordermanager.data.room.dao.MenuItemDao
import agency.five.codebase.android.ordermanager.data.room.dao.NotConfirmedOrderItemDao
import agency.five.codebase.android.ordermanager.data.room.model.DbMenuItem
import agency.five.codebase.android.ordermanager.data.room.model.DbNotConfirmedOrderItem
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DbMenuItem::class,
        DbNotConfirmedOrderItem::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class OrderManagerDatabase : RoomDatabase() {
    abstract fun getMenuItemDao(): MenuItemDao
    abstract fun getNotConfirmedOrderItemDao(): NotConfirmedOrderItemDao
}
