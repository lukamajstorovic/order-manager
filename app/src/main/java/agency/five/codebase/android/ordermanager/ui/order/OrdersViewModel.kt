package agency.five.codebase.android.ordermanager.ui.order

import agency.five.codebase.android.ordermanager.data.repository.order.OrderRepository
import agency.five.codebase.android.ordermanager.ui.order.mapper.OrdersMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class OrdersViewModel(
    private val orderRepository: OrderRepository,
    private val ordersMapper: OrdersMapper,
) : ViewModel() {
    private val _ordersViewState = MutableStateFlow(OrdersViewState(emptyList()))

    val ordersViewState: StateFlow<OrdersViewState> =
        _ordersViewState
            .flatMapLatest {
                orderRepository.allActiveOrders()
                    .map { orders ->
                        ordersMapper.toOrderViewState(orders)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ordersMapper.toOrderViewState(emptyList())
            )

}
