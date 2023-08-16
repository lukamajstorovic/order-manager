package agency.five.codebase.android.ordermanager.data.room.model

import agency.five.codebase.android.ordermanager.enums.StaffRoles
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "staff")
data class DbStaff(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val username: String,
    val password: String,
    val name: String,
    val role: StaffRoles = StaffRoles.WAITER,
)
