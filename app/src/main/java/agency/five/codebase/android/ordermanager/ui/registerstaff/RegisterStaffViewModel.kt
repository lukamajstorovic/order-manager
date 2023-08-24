package agency.five.codebase.android.ordermanager.ui.registerstaff

import agency.five.codebase.android.ordermanager.data.repository.establishment.EstablishmentRepository
import agency.five.codebase.android.ordermanager.data.repository.staff.StaffRepository
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.exceptions.EmptyFieldException
import agency.five.codebase.android.ordermanager.exceptions.ShortPasswordException
import agency.five.codebase.android.ordermanager.exceptions.UserEmailAlreadyExistsException
import agency.five.codebase.android.ordermanager.exceptions.WeakPasswordException
import agency.five.codebase.android.ordermanager.model.Establishment
import agency.five.codebase.android.ordermanager.model.Staff
import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrdersViewState
import agency.five.codebase.android.ordermanager.ui.registerstaff.mapper.EstablishmentMapper
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.regex.Pattern

const val MIN_PASSWORD_LENGTH = 8


class RegisterStaffViewModel(
    private val staffRepository: StaffRepository,
    private val establishmentRepository: EstablishmentRepository,
    private val establishmentMapper: EstablishmentMapper,
) : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _validationResult = mutableStateOf(Result.success("AAA"))
    val validationResult: State<Result<String>> = _validationResult

    val _establishmentCollectionViewState = MutableStateFlow(EstablishmentCollectionViewState(
        emptyList()
    ))
    val establishmentCollectionViewState: StateFlow<EstablishmentCollectionViewState> =
        _establishmentCollectionViewState
            .flatMapLatest {
                establishmentRepository.allEstablishments()
                    .map { establishments ->
                        establishmentMapper.toEstablishmentCollectionViewState(establishments)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = establishmentMapper.toEstablishmentCollectionViewState(emptyList())
            )

    private val _establishmentName = MutableStateFlow("")
    val establishmentName = _establishmentName.asStateFlow()

    fun setName(name: String) {
        _establishmentName.value = name
    }

    fun addStaff(
        name: String,
        username: String,
        password: String,
        establishmentId: String,
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
                        establishmentId = establishmentId,
                        approved = false,
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
                validationResult.value.exceptionOrNull()?.message
                    ?: validationResult.value.getOrNull().toString()

            )
        }
    }

    private fun validateUserData(name: String, username: String, password: String): Result<String> {
        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$"
        );
        if (username.isEmpty() || password.isEmpty() || name.isEmpty()) {
            return Result.failure(EmptyFieldException())
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return Result.failure(ShortPasswordException())
        }

        if (!passwordREGEX.matcher(password).matches()) {
            return Result.failure(WeakPasswordException())
        }
        return Result.success("Information validated")
    }
}
