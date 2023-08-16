package agency.five.codebase.android.ordermanager.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orderedItems")
data class DbOrderedItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val amount: Int = 1,
)
