package agency.five.codebase.android.ordermanager.data.room

import agency.five.codebase.android.ordermanager.data.room.dao.ActiveOrderDao
import agency.five.codebase.android.ordermanager.data.room.dao.MenuItemDao
import agency.five.codebase.android.ordermanager.data.room.dao.OrderedItemDao
import agency.five.codebase.android.ordermanager.data.room.dao.OrderedItemInActiveOrderDao
import agency.five.codebase.android.ordermanager.data.room.dao.StaffDao
import agency.five.codebase.android.ordermanager.data.room.model.DbActiveOrder
import agency.five.codebase.android.ordermanager.data.room.model.DbMenuItem
import agency.five.codebase.android.ordermanager.data.room.model.DbOrderedItem
import agency.five.codebase.android.ordermanager.data.room.model.DbOrderedItemInActiveOrder
import agency.five.codebase.android.ordermanager.data.room.model.DbStaff
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DbMenuItem::class,
        DbOrderedItem::class,
        DbActiveOrder::class,
        DbOrderedItemInActiveOrder::class,
        DbStaff::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class OrderManagerDatabase : RoomDatabase() {
    abstract fun getMenuItemDao(): MenuItemDao
    abstract fun getOrderedItemDao(): OrderedItemDao
    abstract fun getActiveOrderDao(): ActiveOrderDao
    abstract fun getOrderedItemInActiveOrderDao(): OrderedItemInActiveOrderDao
    abstract fun getStaffDao(): StaffDao
}
