package agency.five.codebase.android.ordermanager.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activeOrders")
data class DbActiveOrder(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tableNumber: String,
)
