package agency.five.codebase.android.ordermanager.mock

import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.Staff

object StaffMock {
    fun getStaffList(): List<Staff> = listOf(
        Staff(
            id = 1,
            name = "Luka Majstorovic",
            password = "12345678",
            role = StaffRoles.ADMIN,
        ),
        Staff(
            id = 2,
            name = "Konobar 1",
            password = "87654321",
            role = StaffRoles.WAITER,
        ),
        Staff(
            id = 3,
            name = "Konobar 2",
            password = "11111111",
            role = StaffRoles.WAITER,
        ),
    )
}
