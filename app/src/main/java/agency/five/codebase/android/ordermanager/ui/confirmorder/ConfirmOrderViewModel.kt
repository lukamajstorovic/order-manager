package agency.five.codebase.android.ordermanager.ui.confirmorder

import agency.five.codebase.android.ordermanager.data.currentuser.UserData
import agency.five.codebase.android.ordermanager.data.service.order.OrderService
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
    private val orderService: OrderService,
    private val confirmOrderMapper: ConfirmOrderMapper,
) : ViewModel() {
    private val _confirmOrderViewState = MutableStateFlow(ConfirmOrderViewState(emptyList()))

    val confirmOrderViewState: StateFlow<ConfirmOrderViewState> =
        _confirmOrderViewState
            .flatMapLatest {
                orderService.notConfirmedOrderedItems()
                    .map { orderItems ->
                        confirmOrderMapper.toConfirmOrderViewState(orderItems)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = confirmOrderMapper.toConfirmOrderViewState(emptyList())
            )

    fun confirmOrder(currentUser: UserData, tableNumber: String) {
        viewModelScope.launch {
            orderService.confirmOrder(
                Order(
                    establishmentId = currentUser.establishmentId,
                    tableNumber = tableNumber,
                    createOrderStaffId = currentUser.id,
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
            orderService.subtractNotCompletedOrderItemAmount(orderItemId)
        }
    }
}
