package agency.five.codebase.android.ordermanager.ui.selection

import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.OrderedItem
import agency.five.codebase.android.ordermanager.ui.selection.mapper.SelectionMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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

    fun addMenuItem(menuItem: MenuItem) {
        viewModelScope.launch {
            orderRepository.addMenuItem(menuItem)
        }
    }

    fun removeMenuItem(menuItemId: Int) {
        viewModelScope.launch {
            orderRepository.removeMenuItem(menuItemId)
        }
    }

    fun incrementOrderedItemAmount(menuItemId: Int) {
        viewModelScope.launch {
            orderRepository.incrementOrderedItemAmount(menuItemId)
        }
    }

    fun addOrderedItemOrIncrementAmount(orderedItemName: String) {
        viewModelScope.launch {
            val orderedItems = orderRepository.orderedItems().first()
            val orderedItem = orderedItems.firstOrNull { orderedItemFromList ->
                orderedItemFromList.name == orderedItemName
            }
            if (orderedItem == null) {
                orderRepository.addOrderedItem(
                    OrderedItem(
                        name = orderedItemName
                    )
                )
            } else {
                orderRepository.incrementOrderedItemAmount(orderedItem.id)
            }
        }
    }
}
