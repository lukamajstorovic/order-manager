package agency.five.codebase.android.ordermanager.mock

import agency.five.codebase.android.ordermanager.model.OrderedItem

object OrderedItemMock {
    fun getOrderedItemList(): List<OrderedItem> = listOf(
        OrderedItem(
            id = 1,
            name = "Coca cola 0.5",
            amount = 2,
        ),
        OrderedItem(
            id = 2,
            name = "Osjecko 0.5",
            amount = 2,
        )
    )
}
