package agency.five.codebase.android.ordermanager.mock

import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.Staff
import agency.five.codebase.android.ordermanager.model.User

object StaffMock {
    fun getStaffRole(username: String, password:String): StaffRoles {
        val staff: List<Staff> = getStaff()
        for (user in staff) {
            if (username == user.username && password == user.password) {
                return user.role
            }
        }
        return StaffRoles.NONE
    }
    fun getStaff(): List<Staff> = listOf(
        Staff(
            id = 1,
            username = "lmajstorovic",
            password = "12345678",
            name = "Luka Majstorovic",
            role = StaffRoles.ADMIN,
        ),
        Staff(
            id = 2,
            username = "konobar1",
            password = "87654321",
            name = "Konobar 1",
            role = StaffRoles.WAITER,
        ),
        Staff(
            id = 3,
            username = "konobar2",
            password = "11111111",
            name = "Konobar 2",
            role = StaffRoles.WAITER,
        ),
    )
}
