package agency.five.codebase.android.ordermanager.model

import agency.five.codebase.android.ordermanager.enums.StaffRoles

data class Staff(
    val id: Long,
    val name: String,
    val password: String,
    val role: StaffRoles,
)
