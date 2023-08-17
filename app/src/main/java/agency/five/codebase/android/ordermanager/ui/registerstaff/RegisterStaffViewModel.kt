package agency.five.codebase.android.ordermanager.ui.registerstaff

import agency.five.codebase.android.ordermanager.data.repository.StaffRepository
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.model.Staff
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterStaffViewModel(
    private val staffRepository: StaffRepository,
) : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun addStaff(name: String, username: String, password: String) {
        _isLoading.value = true

        viewModelScope.launch {
            val staff = Staff(
                username = username,
                password = password,
                name = name,
                role = StaffRoles.WAITER,
            )
            try {
                staffRepository.addStaff(staff)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
