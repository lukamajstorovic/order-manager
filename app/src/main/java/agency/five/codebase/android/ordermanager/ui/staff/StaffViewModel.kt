package agency.five.codebase.android.ordermanager.ui.staff

import agency.five.codebase.android.ordermanager.data.repository.staff.StaffRepository
import agency.five.codebase.android.ordermanager.model.Staff
import agency.five.codebase.android.ordermanager.ui.staff.mapper.StaffMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StaffViewModel(
    private val staffRepository: StaffRepository,
    private val staffMapper: StaffMapper,
) : ViewModel() {
    private val _staffViewState = MutableStateFlow(StaffViewState(emptyList()))

    @OptIn(ExperimentalCoroutinesApi::class)
    val staffViewState: StateFlow<StaffViewState> =
        _staffViewState
            .flatMapLatest {
                staffRepository.allStaff()
                    .map { staff ->
                        staffMapper.toStaffViewState(staff)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = staffMapper.toStaffViewState(emptyList())
            )
    fun addStaff(staff: Staff) {
        viewModelScope.launch {
            staffRepository.addStaff(staff)
        }
    }
    fun removeStaff(staffId: String) {
        viewModelScope.launch {
            staffRepository.removeStaff(staffId)
        }
    }
}
