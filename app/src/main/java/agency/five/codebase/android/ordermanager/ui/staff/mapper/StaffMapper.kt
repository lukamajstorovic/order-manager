package agency.five.codebase.android.ordermanager.ui.staff.mapper

import agency.five.codebase.android.ordermanager.model.Staff
import agency.five.codebase.android.ordermanager.ui.staff.StaffViewState

interface StaffMapper {
    fun toStaffViewState(staff: List<Staff>): StaffViewState
}
