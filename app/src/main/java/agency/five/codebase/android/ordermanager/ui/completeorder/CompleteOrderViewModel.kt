package agency.five.codebase.android.ordermanager.ui.completeorder

import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.ui.completeorder.mapper.CompleteOrderMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CompleteOrderViewModel(
    private val orderRepository: OrderRepository,
    private val completeOrderMapper: CompleteOrderMapper,
) : ViewModel() {
    private val _completeOrderViewState = MutableStateFlow(CompleteOrderViewState(0, emptyList()))
    val completeOrderViewState: StateFlow<CompleteOrderViewState> =
        _completeOrderViewState
            .flatMapLatest {
                orderRepository.orderedItemsInActiveOrder(it.orderId)
                    .map { orderedItems ->
                        completeOrderMapper.toCompleteOrderViewState(orderedItems)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CompleteOrderViewState(
                    orderId = 1,
                    completeOrderItemViewStateCollection = emptyList()
                )
            )

    fun completeOrder(activeOrderId: Int) {
        viewModelScope.launch {
            orderRepository.completeOrder(activeOrderId)
        }
    }
}
