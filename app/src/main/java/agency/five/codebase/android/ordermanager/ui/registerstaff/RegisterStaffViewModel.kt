package agency.five.codebase.android.ordermanager.ui.registerstaff

import agency.five.codebase.android.ordermanager.data.repository.StaffRepository
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.exceptions.EmptyFieldException
import agency.five.codebase.android.ordermanager.exceptions.ShortPasswordException
import agency.five.codebase.android.ordermanager.exceptions.UserEmailAlreadyExistsException
import agency.five.codebase.android.ordermanager.exceptions.WeakPasswordException
import agency.five.codebase.android.ordermanager.model.Staff
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

const val  MIN_PASSWORD_LENGTH = 8


class RegisterStaffViewModel(
    private val staffRepository: StaffRepository,
) : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private val _validationResult = mutableStateOf(Result.success("AAA"))
    val validationResult: State<Result<String>> = _validationResult

    fun addStaff(name: String, username: String, password: String) {
        _isLoading.value = true
        println("ISLOADING DONE")
        viewModelScope.launch {
            println("LAUNCHED SCOPE")
            val validationResult = validateUserData(name, username, password)
            println("SUCCESS: " + validationResult.isSuccess)
            println("FAILURE: " + validationResult.isFailure)
            println(validationResult.exceptionOrNull()?.message)
            _validationResult.value = validationResult
            if (validationResult.isSuccess) {
                val staff = Staff(
                    username = username,
                    password = password,
                    name = name,
                    role = StaffRoles.WAITER,
                )
                try {
                    staffRepository.addStaff(staff).run {
                        _validationResult.value = Result.success("Registration successful")
                    }
                } catch (e: UserEmailAlreadyExistsException) {
                    _validationResult.value = Result.failure(e)
                } finally {
                    _isLoading.value = false
                }
            }
            _isLoading.value = false
        }
    }

    private fun validateUserData(name: String, username: String, password: String): Result<String> {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$".toRegex()
        if (username.isEmpty() || password.isEmpty() || name.isEmpty()) {
            println("EMPTYYYY")
            return Result.failure(EmptyFieldException())
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return Result.failure(ShortPasswordException())
        }

        if (!password.matches(passwordPattern)) {
            return Result.failure(WeakPasswordException())
        }
        return Result.success("Information validated")
    }
}
