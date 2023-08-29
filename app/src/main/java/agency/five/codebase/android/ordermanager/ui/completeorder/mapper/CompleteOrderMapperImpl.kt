package agency.five.codebase.android.ordermanager.ui.completeorder.mapper

import agency.five.codebase.android.ordermanager.model.OrderItem
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewState

class CompleteOrderMapperImpl : CompleteOrderMapper {
    override fun toCompleteOrderViewState(
        orderId: String,
        orderItems: List<OrderItem>
    ): CompleteOrderViewState {
        val completeOrderItemViewStateCollection = orderItems.map {
            CompleteOrderItemViewState(
                id = it.id,
                name = it.name,
                amount = it.amount
            )
        }
        return CompleteOrderViewState(
            orderId = orderId,
            completeOrderItemViewStateCollection = completeOrderItemViewStateCollection
        )
    }
}
