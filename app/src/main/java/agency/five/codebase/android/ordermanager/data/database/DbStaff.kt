package agency.five.codebase.android.ordermanager.data.database

import agency.five.codebase.android.ordermanager.enums.StaffRoles
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "staff")
data class DbStaff(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val password: String,
    val role: StaffRoles,
)
