package agency.five.codebase.android.ordermanager.ui.individualstaff

import agency.five.codebase.android.ordermanager.PASSWORD_REGEX
import agency.five.codebase.android.ordermanager.data.repository.staff.StaffRepository
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.exceptions.EmptyFieldException
import agency.five.codebase.android.ordermanager.exceptions.ShortPasswordException
import agency.five.codebase.android.ordermanager.exceptions.UsernameAlreadyExistsException
import agency.five.codebase.android.ordermanager.exceptions.WeakPasswordException
import agency.five.codebase.android.ordermanager.model.Staff
import agency.five.codebase.android.ordermanager.ui.registerstaff.MIN_PASSWORD_LENGTH
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class IndividualStaffViewModel(
    private val staffRepository: StaffRepository,
    private val snackbarHostState: SnackbarHostState,
    private val staffId: String,
) : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _validationResult = mutableStateOf(Result.success(Unit))
    val validationResult: State<Result<Unit>> = _validationResult

    private val _staffData = MutableStateFlow(
        Staff(
            id = "",
            username = "",
            password = "",
            name = "",
            role = StaffRoles.NONE,
            establishmentId = "",
            approved = false,
            createdAt = Timestamp.now(),
        )
    )
    val staffData: StateFlow<Staff> = _staffData

    init {
        getStaffById()
    }

    private fun getStaffById() {
        viewModelScope.launch {
            staffRepository.staffById(staffId).fold(
                onSuccess = { staff ->
                    updateStaffState(staff)
                },
                onFailure = { exception ->
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(exception.message ?: "Failed getting staff by ID.")
                }
            )
        }
    }

    fun updateStaff() {
        _isLoading.value = true

        viewModelScope.launch {
            val dif = async {
                _validationResult.value =
                    validateUserData(staffData.value.name, staffData.value.username, staffData.value.password)
                if (validationResult.value.isSuccess) {
                    _validationResult.value = staffRepository.updateStaff(staffData.value)
                }
            }
            dif.await()
            _isLoading.value = false
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                validationResult.value.exceptionOrNull()?.message
                    ?: "Updated successfully."
            )
        }
    }

    fun updateStaffState(updatedStaff: Staff) {
        _staffData.value = updatedStaff
    }

    private suspend fun validateUserData(
        name: String,
        username: String,
        password: String,
    ): Result<Unit> {
        val passwordREGEX = Pattern.compile(PASSWORD_REGEX);
        if (username.isEmpty() || password.isEmpty() || name.isEmpty()) {
            return Result.failure(EmptyFieldException())
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return Result.failure(ShortPasswordException())
        }

        if (!passwordREGEX.matcher(password).matches()) {
            return Result.failure(WeakPasswordException())
        }

        return Result.success(Unit)
    }
}
