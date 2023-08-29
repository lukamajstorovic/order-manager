package agency.five.codebase.android.ordermanager.ui.completeorder

import agency.five.codebase.android.ordermanager.data.repository.order.OrderRepository
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
    private val orderId: String,
) : ViewModel() {
    private val _completeOrderViewState = MutableStateFlow(CompleteOrderViewState(orderId, emptyList()))
    val completeOrderViewState: StateFlow<CompleteOrderViewState> =
        _completeOrderViewState
            .flatMapLatest {
                orderRepository.orderItems(it.orderId)
                    .map { orderItems ->
                        completeOrderMapper.toCompleteOrderViewState(orderId, orderItems)
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

    fun completeOrder(orderId: String) {
        viewModelScope.launch {
            orderRepository.completeOrder(orderId)
        }
    }
}
