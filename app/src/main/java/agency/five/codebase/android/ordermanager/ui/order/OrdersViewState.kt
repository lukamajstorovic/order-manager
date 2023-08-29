package agency.five.codebase.android.ordermanager.ui.order


data class OrderItemViewState(
    val id: String,
    val tableNumber: String,
)

data class OrdersViewState(
    val orderItemViewStateCollection: List<OrderItemViewState>
)
