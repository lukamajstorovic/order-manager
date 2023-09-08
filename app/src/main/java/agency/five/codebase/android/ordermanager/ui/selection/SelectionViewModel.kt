package agency.five.codebase.android.ordermanager.ui.selection

import agency.five.codebase.android.ordermanager.data.service.menuItem.MenuItemService
import agency.five.codebase.android.ordermanager.data.service.order.OrderService
import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.ui.selection.mapper.SelectionMapper
import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SelectionViewModel(
    private val orderService: OrderService,
    private val menuItemService: MenuItemService,
    private val selectionMapper: SelectionMapper,
    private val establishmentId: String,
    private val snackbarHostState: SnackbarHostState,
) : ViewModel() {
    private val _selectionViewState = MutableStateFlow(SelectionViewState(emptyList()))

    @OptIn(ExperimentalCoroutinesApi::class)
    val selectionViewState: StateFlow<SelectionViewState> =
        _selectionViewState
            .flatMapLatest {
                menuItemService.getMenuItems(establishmentId = establishmentId)
                    .map { menuItems ->
                        selectionMapper.toSelectionViewState(menuItems)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = selectionMapper.toSelectionViewState(emptyList())
            )

    fun addOrderItemOrIncrementAmount(orderItemName: String) {
        viewModelScope.launch {
            val orderItems = orderService.notConfirmedOrderedItems().first()
            val orderItem = orderItems.firstOrNull { orderItemFromList ->
                orderItemFromList.name == orderItemName
            }
            if (orderItem == null) {
                orderService.addNotConfirmedOrderedItem(
                    NotConfirmedOrderItem(
                        name = orderItemName,
                        amount = 1,
                    )
                )
            } else {
                orderService.incrementNotCompletedOrderItemAmount(orderItem.name)
            }
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                "Successfully added item"
            )
        }
    }
}
