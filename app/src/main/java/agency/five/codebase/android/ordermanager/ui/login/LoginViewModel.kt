package agency.five.codebase.android.ordermanager.ui.login

import agency.five.codebase.android.ordermanager.data.repository.StaffRepository
import agency.five.codebase.android.ordermanager.enums.StaffRoles
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

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun authenticateStaff(username: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val staff = staffRepository.staffByCredentials(username, password)
                if (staff != null) {
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
