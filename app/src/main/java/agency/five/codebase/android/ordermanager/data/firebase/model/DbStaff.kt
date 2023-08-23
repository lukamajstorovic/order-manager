package agency.five.codebase.android.ordermanager.data.firebase.model

import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.model.Staff
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId


data class DbStaff(
    @DocumentId
    val id: String = "placeholder",
    val name: String,
    val username: String,
    val password: String,
    val role: StaffRoles,
    val establishmentId: String,
    val approved: Boolean,
    val createdAt: Timestamp = Timestamp.now(),
) {
    fun toStaff() =
        Staff(
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
