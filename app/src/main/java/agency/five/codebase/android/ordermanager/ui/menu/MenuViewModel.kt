package agency.five.codebase.android.ordermanager.ui.menu

import agency.five.codebase.android.ordermanager.data.repository.menuItem.MenuItemRepository
import agency.five.codebase.android.ordermanager.exceptions.EmptyFieldException
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.ui.order.OrdersViewState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MenuViewModel(
    private val snackbarHostState: SnackbarHostState,
    private val menuItemRepository: MenuItemRepository,
    private val establishmentId: String,
) : ViewModel() {

    private val _validationResult = mutableStateOf(Result.success(Unit))
    val validationResult: State<Result<Unit>> = _validationResult

    private val _deletionResult = mutableStateOf(Result.success(Unit))
    val deletionResult: State<Result<Unit>> = _deletionResult

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())

    val menuItems: StateFlow<List<MenuItem>> =
        _menuItems
            .flatMapLatest {
                menuItemRepository.getMenuItems(establishmentId)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

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

    fun deleteMenuItem(id: String) {
        viewModelScope.launch {
            val dif = async {
                _deletionResult.value = menuItemRepository.removeMenuItem(id)
            }
            dif.await()
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                deletionResult.value.exceptionOrNull()?.message
                    ?: "Deleted successfully"
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
