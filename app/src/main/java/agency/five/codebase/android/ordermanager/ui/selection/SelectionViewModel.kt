package agency.five.codebase.android.ordermanager.ui.selection

import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.OrderedItem
import agency.five.codebase.android.ordermanager.ui.selection.mapper.SelectionMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SelectionViewModel(
    private val orderRepository: OrderRepository,
    private val selectionMapper: SelectionMapper,
) : ViewModel() {
    private val _selectionViewState = MutableStateFlow(SelectionViewState(emptyList()))

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

    fun addOrderedItemAmount(menuItemId: Int) {
        viewModelScope.launch {
            orderRepository.addOrderedItemAmount(menuItemId)
        }
    }

    fun addOrderedItem(orderedItemName: String) {
        viewModelScope.launch {
            var flag = false
            val orderedItems = orderRepository.orderedItems()
            orderedItems.map {
                it.forEach { orderedItemFromList ->
                    if (orderedItemFromList.name == orderedItemName) {
                        addOrderedItemAmount(orderedItemFromList.id)
                        flag = true
                    }
                }
            }
            if (flag) {
                flag = false
            } else {
                orderRepository.addOrderedItem(
                    OrderedItem(
                        name = orderedItemName
                    )
                )
            }
        }
    }

    /*
    fun addOrderedItem(orderedItem: OrderedItem) {
        viewModelScope.launch {
            var flag = false
            val orderedItems = orderRepository.orderedItems()
            orderedItems.map {
                it.forEach { orderedItemFromList ->
                    if(orderedItemFromList.id == orderedItem.id) {
                        flag = true
                    }
                }
            }
            if(flag){
                addOrderedItemAmount(orderedItem.id)
                flag = false
            }else{
                orderRepository.addOrderedItem(orderedItem)
            }
        }
    }

     */
    /*
    fun confirmOrder(activeOrderId: Int) {
        viewModelScope.launch {
            orderRepository.confirmOrder(activeOrderId)
        }
    }

    fun completeOrder(activeOrderId: Int) {
        viewModelScope.launch {
            orderRepository.completeOrder(activeOrderId)
        }
    }
     */
}
