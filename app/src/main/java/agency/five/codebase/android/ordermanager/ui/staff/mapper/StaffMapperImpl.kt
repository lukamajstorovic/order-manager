package agency.five.codebase.android.ordermanager.ui.staff.mapper

import agency.five.codebase.android.ordermanager.model.Staff
import agency.five.codebase.android.ordermanager.ui.component.staff.StaffCardViewState
import agency.five.codebase.android.ordermanager.ui.staff.StaffViewState

class StaffMapperImpl : StaffMapper {
    override fun toStaffViewState(staff: List<Staff>): StaffViewState {
        val staffCardViewStateCollection = staff.map { mapStaff(it) }
        return StaffViewState(
            staffCardViewStateCollection = staffCardViewStateCollection
        )
    }

    private fun mapStaff(staff: Staff): StaffCardViewState {
        return StaffCardViewState(
            id = staff.id,
            name = staff.name,
            role = staff.role,
        )
    }
}
