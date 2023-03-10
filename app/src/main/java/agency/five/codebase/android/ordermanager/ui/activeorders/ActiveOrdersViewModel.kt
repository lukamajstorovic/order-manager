package agency.five.codebase.android.ordermanager.ui.activeorders

import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.ui.activeorders.mapper.ActiveOrdersMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ActiveOrdersViewModel(
    private val orderRepository: OrderRepository,
    private val activeOrdersMapper: ActiveOrdersMapper,
) : ViewModel() {
    private val _activeOrdersViewState = MutableStateFlow(ActiveOrdersViewState(emptyList()))

    val activeOrdersViewState: StateFlow<ActiveOrdersViewState> =
        _activeOrdersViewState
            .flatMapLatest {
                orderRepository.activeOrders()
                    .map { activeOrders ->
                        activeOrdersMapper.toActiveOrderViewState(activeOrders)
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = activeOrdersMapper.toActiveOrderViewState(emptyList())
            )

    fun completeOrder(activeOrderId: Int) {
        viewModelScope.launch {
            orderRepository.completeOrder(activeOrderId)
        }
    }
}
