package agency.five.codebase.android.ordermanager.ui.order

import com.google.firebase.Timestamp


data class OrderItemViewState(
    val id: String,
    val tableNumber: String,
)

data class OrdersViewState(
    val orderItemViewStateCollection: List<OrderItemViewState>
)

data class CompletedOrderItemViewState(
    val id: String,
    val tableNumber: String,
    val createOrderStaffId: String,
    val completeOrderStaffId: String,
    val createdAt: Timestamp,
)

data class CompletedOrdersViewState(
    val completedOrderItemViewStateCollection: List<CompletedOrderItemViewState>
)
