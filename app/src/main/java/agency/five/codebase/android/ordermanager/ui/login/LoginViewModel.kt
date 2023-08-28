package agency.five.codebase.android.ordermanager.ui.login

import agency.five.codebase.android.ordermanager.data.repository.staff.StaffRepository
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.model.Staff
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val staffRepository: StaffRepository
) : ViewModel() {
    private val _staffRole = mutableStateOf(StaffRoles.NONE)
    val staffRole: State<StaffRoles> = _staffRole

    private val _staff = mutableStateOf(
        Staff(
            username = "",
            password = "",
            name = "",
            role = StaffRoles.NONE,
            establishmentId = "",
            approved = false,
        )
    )
    val staff: State<Staff> = _staff

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun authenticateStaff(username: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val staff = staffRepository.staffByCredentials(username, password)
                if (staff != null) {
                    _staff.value = staff
                    _staffRole.value = staff.role
                } else {
                    _staffRole.value = StaffRoles.NONE
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}
