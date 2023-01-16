package agency.five.codebase.android.ordermanager.data.repository

/*
class FakeOrderRepository(
    private val ioDispatcher: CoroutineDispatcher,
) : OrderRepository {
    private val fakeMenuItems = MenuItemMock.getMenuItemList().toMutableList()
    private val menuItems: Flow<List<MenuItem>> = MenuItemsDBMock.menuItemIds
        .mapLatest { menuItemIds ->
            fakeMenuItems.map { menuItem ->
                menuItem.copy()
            }
        }

    private val fakeOrderedItems = OrderedItemMock.getOrderedItemList().toMutableList()
    private val orderedItems: Flow<List<OrderedItem>> = OrderedItemsDBMock.orderedItemIds
        .mapLatest { orderedItemsIds ->
            fakeOrderedItems.map { orderedItem ->
                orderedItem.copy()
            }
        }

    private val fakeActiveOrders = ActiveOrderMock.getActiveOrders().toMutableList()
    private val activeOrders: Flow<List<ActiveOrder>> = ActiveOrdersDBMock.activeOrderIds
        .mapLatest { activeOrderIds ->
            fakeActiveOrders.map { activeOrder ->
                activeOrder.copy()
            }
        }

    override fun menuItems(): Flow<List<MenuItem>> = menuItems
    override fun orderedItems(): Flow<List<OrderedItem>> = orderedItems
    override fun activeOrders(): Flow<List<ActiveOrder>> = activeOrders
    /*

    override suspend fun orderedItemsInActiveOrder(activeOrderId: Int): Flow<List<OrderedItemInActiveOrder>> {
        val items = mutableListOf<OrderedItemInActiveOrder>()
        return null
    }

    override suspend fun orderedItemsInActiveOrder(activeOrderId: Int): Flow<List<OrderedItem>> {
        val orders = ActiveOrderMock.getActiveOrders()
        val order = orders.filter {
            it.id == activeOrderId
        }[0]
        val items = mutableListOf<OrderedItem>()
        order.itemIdCollection.forEach {
            items.add(getItemFromId(it))
        }
        return OrderedItemsDBMock.orderedItemIds
            .mapLatest { orderedItemsIds ->
                items.map { orderedItem ->
                    orderedItem.copy()
                }
            }
    }

     */

    private fun getItemFromId(id: Int): OrderedItem {
        fakeOrderedItems.forEach {
            if (it.id == id)
                return it
        }
        return OrderedItem(
            id = 32423434,
            name = "",
            amount = 1
        )
    }

    override suspend fun addOrderedItem(orderedItemId: Int) {
        OrderedItemsDBMock.insert(orderedItemId)
    }

    override suspend fun removeOrderedItem(orderedItemId: Int) {
        OrderedItemsDBMock.delete(orderedItemId)
    }

    override suspend fun confirmOrder() {
        ActiveOrdersDBMock.insert(2)
    }

    override suspend fun completeOrder(activeOrderId: Int) {
        ActiveOrdersDBMock.delete(activeOrderId)
    }
    //TODO("dodati za menu items dodavanje i removanje")
}


 */
