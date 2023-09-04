package agency.five.codebase.android.ordermanager.ui.menu

import agency.five.codebase.android.ordermanager.data.repository.menuItem.MenuItemRepository
import agency.five.codebase.android.ordermanager.exceptions.EmptyFieldException
import agency.five.codebase.android.ordermanager.model.MenuItem
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MenuViewModel(
    private val snackbarHostState: SnackbarHostState,
    private val menuItemRepository: MenuItemRepository,
    private val establishmentId: String,
) : ViewModel() {

    private val _validationResult = mutableStateOf(Result.success(Unit))
    val validationResult: State<Result<Unit>> = _validationResult

    fun addMenuItem(
        name: String
    ) {
        viewModelScope.launch {
            val dif = async {
                _validationResult.value =
                    validateMenuItemName(name)
                if (validationResult.value.isSuccess) {
                    val menuItem = MenuItem(
                        id = "placeholder",
                        name = name,
                        establishmentId = establishmentId,
                    )
                    _validationResult.value = menuItemRepository.addMenuItem(menuItem)
                }
            }
            dif.await()
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                validationResult.value.exceptionOrNull()?.message
                    ?: "Added successfully"
            )
        }
    }

    private fun validateMenuItemName(name: String): Result<Unit> {
        return if (name.isEmpty()) {
            Result.failure(EmptyFieldException())
        } else {
            Result.success(Unit)
        }
    }
}
