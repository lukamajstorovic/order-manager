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
                refreshApprovedStaffViewState()
                refreshNotApprovedStaffViewState()
            }
        }
    }

    private val _notApprovedStaffViewState = MutableStateFlow(StaffViewState(emptyList()))
    val notApprovedStaffViewState: StateFlow<StaffViewState> = _notApprovedStaffViewState

    private val _approvedStaffViewState = MutableStateFlow(StaffViewState(emptyList()))
    val approvedStaffViewState: StateFlow<StaffViewState> = _approvedStaffViewState

    private fun refreshApprovedStaffViewState() {
        viewModelScope.launch {
            staffRepository.getApprovedStaff(currentEstablishmentId ?: "")
                .collect { staffList ->
                    val staffViewState = staffMapper.toStaffViewState(staffList)
                    _approvedStaffViewState.value = staffViewState
                }
        }
    }

    private fun refreshNotApprovedStaffViewState() {
        viewModelScope.launch {
            staffRepository.getNotApprovedStaff(currentEstablishmentId ?: "")
                .collect { staffList ->
                    val staffViewState = staffMapper.toStaffViewState(staffList)
                    _notApprovedStaffViewState.value = staffViewState
                }
        }
    }
}
