package agency.five.codebase.android.ordermanager.data.currentuser

import agency.five.codebase.android.ordermanager.enums.StaffRoles
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: String = "",
    val username: String = "",
    val name: String = "",
    val role: StaffRoles = StaffRoles.NONE,
    val establishmentId: String = "",
)
