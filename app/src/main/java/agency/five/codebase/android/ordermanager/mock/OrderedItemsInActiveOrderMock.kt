package agency.five.codebase.android.ordermanager.mock

import agency.five.codebase.android.ordermanager.model.OrderedItem
import agency.five.codebase.android.ordermanager.model.OrderedItemInActiveOrder

object OrderedItemsInActiveOrderMock {
    fun getOrderedItemList(): List<OrderedItemInActiveOrder> = listOf(
        OrderedItemInActiveOrder(
            id = 1,
            name = "Coca cola 0.5",
            amount = 2,
            activeOrderId = 1,
        ),
        OrderedItemInActiveOrder(
            id = 2,
            name = "Osjecko 0.5",
            amount = 2,
            activeOrderId = 1,
        )
    )
}
