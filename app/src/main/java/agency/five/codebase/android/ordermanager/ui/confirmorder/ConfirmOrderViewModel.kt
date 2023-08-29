package agency.five.codebase.android.ordermanager.ui.confirmorder

import agency.five.codebase.android.ordermanager.data.repository.order.OrderRepository
import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.ui.confirmorder.mapper.ConfirmOrderMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConfirmOrderViewModel(
    private val orderRepository: OrderRepository,
    private val confirmOrderMapper: ConfirmOrderMapper,
) : ViewModel() {
    private val _confirmOrderViewState = MutableStateFlow(ConfirmOrderViewState(emptyList()))

    val confirmOrderViewState: StateFlow<ConfirmOrderViewState> =
        _confirmOrderViewState
            .flatMapLatest {
                orderRepository.notConfirmedOrderedItems()
                    .map { orderItems ->
                        confirmOrderMapper.toConfirmOrderViewState(orderItems)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = confirmOrderMapper.toConfirmOrderViewState(emptyList())
            )

    fun confirmOrder(currentUserId: String, tableNumber: String) {
        viewModelScope.launch {
            orderRepository.confirmOrder(
                Order(
                    tableNumber = tableNumber,
                    createOrderStaffId = currentUserId,
                    completeOrderStaffId = "",
                    active = true,
                )
            ).fold(
                onSuccess = {
                    println("MANAGED: $it")
                },
                onFailure = {
                    println("FAILED: $it")
                }
            )
        }
    }

    val text = MutableStateFlow("")

    fun subtractOrderItemAmount(orderItemId: Int) {
        viewModelScope.launch {
            orderRepository.subtractNotCompletedOrderItemAmount(orderItemId)
        }
    }
}
