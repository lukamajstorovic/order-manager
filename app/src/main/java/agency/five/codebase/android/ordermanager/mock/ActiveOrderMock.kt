package agency.five.codebase.android.ordermanager.mock

import agency.five.codebase.android.ordermanager.model.ActiveOrder


object ActiveOrderMock {
    fun getActiveOrders(): List<ActiveOrder> = listOf(
        ActiveOrder(
            id = 1,
            tableNumber = "13",
        ),
        ActiveOrder(
            id = 2,
            tableNumber = "14",
        )
    )
}
