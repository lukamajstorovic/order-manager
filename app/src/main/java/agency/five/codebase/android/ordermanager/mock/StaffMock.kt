package agency.five.codebase.android.ordermanager.mock

import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.model.Staff
import com.google.firebase.Timestamp

object StaffMock {
    fun getStaff(): List<Staff> = listOf(
        Staff(
            id = "1",
            username = "lmajstorovic",
            password = "12345678",
            name = "Luka Majstorovic",
            role = StaffRoles.ADMIN,
            createdAt = Timestamp.now(),
        ),
        Staff(
            id = "2",
            username = "konobar1",
            password = "87654321",
            name = "Konobar 1",
            role = StaffRoles.WAITER,
            createdAt = Timestamp.now(),
        ),
        Staff(
            id = "3",
            username = "konobar2",
            password = "11111111",
            name = "Konobar 2",
            role = StaffRoles.WAITER,
            createdAt = Timestamp.now(),
        ),
    )
}
