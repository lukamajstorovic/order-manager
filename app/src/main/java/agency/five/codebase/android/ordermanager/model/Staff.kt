package agency.five.codebase.android.ordermanager.model

import agency.five.codebase.android.ordermanager.enums.StaffRoles

data class Staff(
    val id: Long,
    val username: String,
    val password: String,
    val name: String,
    val role: StaffRoles,
)