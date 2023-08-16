package agency.five.codebase.android.ordermanager.model

import agency.five.codebase.android.ordermanager.enums.StaffRoles
import com.google.firebase.Timestamp

data class Staff(
    val id: String = "placeholder",
    val username: String,
    val password: String,
    val name: String,
    val role: StaffRoles = StaffRoles.WAITER,
    val createdAt: Timestamp = Timestamp.now(),
)
