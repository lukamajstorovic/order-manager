package agency.five.codebase.android.ordermanager.ui.completeorder.mapper

import agency.five.codebase.android.ordermanager.model.OrderItem
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewState

interface CompleteOrderMapper {
    fun toCompleteOrderViewState(orderId: String,orderItems: List<OrderItem>): CompleteOrderViewState
}
