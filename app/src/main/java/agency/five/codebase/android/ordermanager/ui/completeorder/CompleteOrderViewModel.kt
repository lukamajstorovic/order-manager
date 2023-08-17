package agency.five.codebase.android.ordermanager.ui.completeorder

import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.ui.completeorder.mapper.CompleteOrderMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CompleteOrderViewModel(
    private val orderRepository: OrderRepository,
    private val completeOrderMapper: CompleteOrderMapper,
    private val orderId: Int,
) : ViewModel() {
    private val _completeOrderViewState = MutableStateFlow(CompleteOrderViewState(orderId, emptyList()))
    val completeOrderViewState: StateFlow<CompleteOrderViewState> =
        _completeOrderViewState
            .flatMapLatest {
                orderRepository.orderedItemsInActiveOrder(it.orderId)
                    .map { orderedItems ->
                        completeOrderMapper.toCompleteOrderViewState(orderId, orderedItems)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CompleteOrderViewState(
                    orderId = orderId,
                    completeOrderItemViewStateCollection = emptyList()
                )
            )

    fun completeOrder(activeOrderId: Int) {
        viewModelScope.launch {
            orderRepository.completeOrder(activeOrderId)
        }
    }
}
