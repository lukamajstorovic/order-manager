package agency.five.codebase.android.ordermanager.ui.registerstaff

import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.data.repository.StaffRepository
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.OrderedItem
import agency.five.codebase.android.ordermanager.model.Staff
import agency.five.codebase.android.ordermanager.ui.selection.SelectionViewState
import agency.five.codebase.android.ordermanager.ui.selection.mapper.SelectionMapper
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
