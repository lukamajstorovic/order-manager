package agency.five.codebase.android.ordermanager.model

import agency.five.codebase.android.ordermanager.data.firebase.model.DbStaff
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import com.google.firebase.Timestamp

data class Staff(
    val id: String = "placeholder",
    val username: String,
    val password: String,
    val name: String,
    val role: StaffRoles = StaffRoles.WAITER,
    val establishmentId: String,
    val approved: Boolean,
    val createdAt: Timestamp = Timestamp.now(),
) {
    fun toDbStaff() =
        DbStaff(
            id = id,
            name = name,
            username = username,
            password = password,
            role = role,
            establishmentId = establishmentId,
            approved = approved,
            createdAt = createdAt,
        )
}
