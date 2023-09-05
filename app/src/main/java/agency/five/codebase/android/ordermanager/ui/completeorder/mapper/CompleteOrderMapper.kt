package agency.five.codebase.android.ordermanager.ui.completeorder.mapper

import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.model.OrderItem
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewState
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrdersViewState

interface CompleteOrderMapper {
    fun toCompleteOrderViewState(
        orderId: String,
        orderItems: List<OrderItem>
    ): CompleteOrderViewState

    fun toCompletedOrderViewState(
        order: Order,
        orderItems: List<OrderItem>
    ): CompletedOrdersViewState
}
