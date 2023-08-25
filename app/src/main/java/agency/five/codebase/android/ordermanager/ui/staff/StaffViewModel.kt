package agency.five.codebase.android.ordermanager.ui.staff

import agency.five.codebase.android.ordermanager.data.currentuser.UserDataViewModel
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
    private val _establishmentId = MutableStateFlow("")
    val establishmentId: StateFlow<String> = _establishmentId

    private var currentEstablishmentId: String? = null

    fun updateEstablishmentId(userDataViewModel: UserDataViewModel) {
        viewModelScope.launch {
            userDataViewModel.establishmentId.collect { newId ->
                currentEstablishmentId = newId
                refreshStaffViewState()
            }
        }
    }

    private val _staffViewState = MutableStateFlow(StaffViewState(emptyList()))
    val staffViewState: StateFlow<StaffViewState> = _staffViewState

    private fun refreshStaffViewState() {
        viewModelScope.launch {
            staffRepository.staffByEstablishment(currentEstablishmentId ?: "")
                .collect { staffList ->
                    val staffViewState = staffMapper.toStaffViewState(staffList)
                    _staffViewState.value = staffViewState
                }
        }
    }

    /*private val _staffViewState = MutableStateFlow(StaffViewState(emptyList()))

    @OptIn(ExperimentalCoroutinesApi::class)
    val staffViewState: StateFlow<StaffViewState>
        get() = if (_establishmentId.value.isNotEmpty()) {
            _staffViewState
                .flatMapLatest { staffRepository.staffByEstablishment(_establishmentId.value) }
                .map { staff ->
                    staffMapper.toStaffViewState(staff)
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = staffMapper.toStaffViewState(emptyList())
                )
        } else {
            MutableStateFlow(staffMapper.toStaffViewState(emptyList()))
        }*/

    fun updateEstablishmentId(newId: String) {
        _establishmentId.value = newId
    }

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
