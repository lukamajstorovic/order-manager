package agency.five.codebase.android.ordermanager.ui.login

import agency.five.codebase.android.ordermanager.data.repository.StaffRepository
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrdersViewState
import agency.five.codebase.android.ordermanager.ui.staff.StaffRoute
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*class LoginViewModel(
    private val staffRepository: StaffRepository
) : ViewModel() {
    private val _authenticatedRole = MutableStateFlow(StaffRoles.NONE)


    *//*fun getAuthenticatedRole(username: String, password: String) : StateFlow<StaffRoles> {
        viewModelScope.launch {
            authenticatedRole.emit(authenticateStaff(username, password))
        }
        return authenticatedRole
    }*//*

    private suspend fun authenticateStaff(username: String, password: String): StaffRoles {
        val result = runBlocking {
            staffRepository.allStaff().collect { staffCollection ->
                var authenticated = false
                staffCollection.forEach { staff ->
                    if (username == staff.username && password == staff.password) {
                        return staff.role
                    }
                }
                if (!authenticated) {
                    StaffRoles.NONE
                }
            }
        }
        return result
    }
}*/

class LoginViewModel(
    private val staffRepository: StaffRepository
) : ViewModel() {
    /*private val _authenticatedRole = MutableStateFlow(StaffRoles.NONE)
    private val authenticatedRole = _authenticatedRole
        .flatMapLatest {
            staffRepository.allStaff()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun authenticateStaff(username: String, password: String): StateFlow<StaffRoles> {
        *//*val staff = staffRepository.staffByCredentials(username, password)
        return staff?.role ?: StaffRoles.NONE*//*
        var foundUser = false
        val role = authenticatedRole.map { staffCollection ->
            staffCollection.forEach {staff ->
                if(staff.username == username && staff.password == password) {
                    foundUser = true
                    staff.role
                }
            }
            if(!foundUser) {
                StaffRoles.NONE
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), StaffRoles.NONE)

        return role
    }*/
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
