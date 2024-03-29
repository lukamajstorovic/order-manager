package agency.five.codebase.android.ordermanager.ui.order

import agency.five.codebase.android.ordermanager.data.service.order.OrderService
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
    private val orderService: OrderService,
    private val ordersMapper: OrdersMapper,
    private val establishmentId: String,
) : ViewModel() {
    private val _ordersViewState = MutableStateFlow(OrdersViewState(emptyList()))

    val ordersViewState: StateFlow<OrdersViewState> =
        _ordersViewState
            .flatMapLatest {
                orderService.allActiveOrders(establishmentId)
                    .map { orders ->
                        ordersMapper.toOrderViewState(orders)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ordersMapper.toOrderViewState(emptyList())
            )

    private val _completedOrdersViewState =
        MutableStateFlow(CompletedOrderViewStateItemCollectionViewState(emptyList()))

    val completedOrdersViewState: StateFlow<CompletedOrderViewStateItemCollectionViewState> =
        _completedOrdersViewState
            .flatMapLatest {
                orderService.allCompletedOrders(establishmentId)
                    .map { orders ->
                        ordersMapper.toCompletedOrdersMinimalInfoViewState(orders)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ordersMapper.toCompletedOrdersMinimalInfoViewState(emptyList())
            )
}
