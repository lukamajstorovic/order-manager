package agency.five.codebase.android.ordermanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DbMenuItem::class,
        DbOrderedItem::class,
        DbActiveOrder::class,
        DbOrderedItemInActiveOrder::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class OrderManagerDatabase : RoomDatabase() {
    abstract fun getMenuItemDao(): MenuItemDao
    abstract fun getOrderedItemDao(): OrderedItemDao
    abstract fun getActiveOrderDao(): ActiveOrderDao
    abstract fun getOrderedItemInActiveOrderDao(): OrderedItemInActiveOrderDao
}
