package agency.five.codebase.android.ordermanager.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menuItems")
data class DbMenuItem(
    @PrimaryKey val id: Int,
    val name: String,
    val iconName: String,
)
