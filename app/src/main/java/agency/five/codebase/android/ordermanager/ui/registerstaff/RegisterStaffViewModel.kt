package agency.five.codebase.android.ordermanager.ui.registerstaff

import agency.five.codebase.android.ordermanager.data.repository.StaffRepository
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.exceptions.EmptyFieldException
import agency.five.codebase.android.ordermanager.exceptions.ShortPasswordException
import agency.five.codebase.android.ordermanager.exceptions.UserEmailAlreadyExistsException
import agency.five.codebase.android.ordermanager.exceptions.WeakPasswordException
import agency.five.codebase.android.ordermanager.model.Staff
import agency.five.codebase.android.ordermanager.navigation.NavigationItem
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

const val MIN_PASSWORD_LENGTH = 8


class RegisterStaffViewModel(
    private val staffRepository: StaffRepository,
) : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private val _validationResult = mutableStateOf(Result.success("AAA"))
    val validationResult: State<Result<String>> = _validationResult

    fun addStaff(
        name: String,
        username: String,
        password: String,
        snackbarHostState: SnackbarHostState,
        onNavigate: () -> Unit,
        ) {
        _isLoading.value = true
        println("ISLOADING DONE")
        viewModelScope.launch {
            val dif = async {
                println("LAUNCHED SCOPE")
                _validationResult.value = validateUserData(name, username, password)
                if (validationResult.value.isSuccess) {
                    val staff = Staff(
                        username = username,
                        password = password,
                        name = name,
                        role = StaffRoles.WAITER,
                    )
                    try {
                        staffRepository.addStaff(staff).run {
                            _validationResult.value = Result.success("Registration successful")
                        }.run {
                            onNavigate()
                        }
                    } catch (e: UserEmailAlreadyExistsException) {
                        _validationResult.value = Result.failure(e)
                    } finally {
                        _isLoading.value = false
                    }
                }
            }
            dif.await()
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                validationResult.value.exceptionOrNull()?.message ?:
                validationResult.value.getOrNull().toString()

            )
        }
    }

    private fun validateUserData(name: String, username: String, password: String): Result<String> {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])$".toRegex()
        if (username.isEmpty() || password.isEmpty() || name.isEmpty()) {
            println("EMPTYYYY")
            return Result.failure(EmptyFieldException())
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return Result.failure(ShortPasswordException())
        }

        if (password.matches(passwordPattern)) {
            return Result.failure(WeakPasswordException())
        }
        return Result.success("Information validated")
    }
}
