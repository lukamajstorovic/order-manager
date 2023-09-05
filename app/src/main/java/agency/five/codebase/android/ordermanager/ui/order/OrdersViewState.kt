package agency.five.codebase.android.ordermanager.ui.order

import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderItemViewState
import com.google.firebase.Timestamp


data class OrderItemViewState(
    val id: String,
    val tableNumber: String,
)

data class OrdersViewState(
    val orderItemViewStateCollection: List<OrderItemViewState>
)

data class CompleteOrderViewStateItem(
    val id: String,
    val tableNumber: String,
    val createdAt: Timestamp,
)

data class CompletedOrderViewStateItemCollectionViewState(
    val completedOrderViewStateItemCollection: List<CompleteOrderViewStateItem>
)

data class CompletedOrdersViewState(
    val id: String,
    val tableNumber: String,
    val createOrderStaffId: String,
    val completeOrderStaffId: String,
    val createdAt: Timestamp,
    val completedOrderItemViewStateCollection: List<CompleteOrderItemViewState>
)
