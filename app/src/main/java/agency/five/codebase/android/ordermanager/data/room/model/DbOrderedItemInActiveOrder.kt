package agency.five.codebase.android.ordermanager.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "orderedItemsInActiveOrder",
    foreignKeys = [ForeignKey(
        entity = DbActiveOrder::class,
        parentColumns = ["id"],
        childColumns = ["activeOrderId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class DbOrderedItemInActiveOrder(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val amount: Int,
    @ColumnInfo(name = "activeOrderId") val activeOrderId: Long,
)
