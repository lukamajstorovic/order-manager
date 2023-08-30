package agency.five.codebase.android.ordermanager.ui.selection

import agency.five.codebase.android.ordermanager.data.repository.order.OrderRepository
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.OrderItem
import agency.five.codebase.android.ordermanager.ui.selection.mapper.SelectionMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.math.BigDecimal

class SelectionViewModel(
    private val orderRepository: OrderRepository,
    private val selectionMapper: SelectionMapper,
) : ViewModel() {
    private val _selectionViewState = MutableStateFlow(SelectionViewState(emptyList()))

    @OptIn(ExperimentalCoroutinesApi::class)
    val selectionViewState: StateFlow<SelectionViewState> =
        _selectionViewState
            .flatMapLatest {
                orderRepository.menuItems()
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
            val orderItems = orderRepository.notConfirmedOrderedItems().first()
            val orderItem = orderItems.firstOrNull { orderItemFromList ->
                orderItemFromList.name == orderItemName
            }
            if (orderItem == null) {
                orderRepository.addNotConfirmedOrderedItem(
                    NotConfirmedOrderItem(
                        name = orderItemName,
                        amount = 1,
                    )
                )
            } else {
                orderRepository.incrementNotCompletedOrderItemAmount(orderItem.name)
            }
        }
    }
}
