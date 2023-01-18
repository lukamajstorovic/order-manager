package agency.five.codebase.android.ordermanager.ui.completeorder.mapper

import agency.five.codebase.android.ordermanager.model.OrderedItemInActiveOrder
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewState

class CompleteOrderMapperImpl(
) : CompleteOrderMapper {
    override fun toCompleteOrderViewState(
        orderId: Int,
        orderedItems: List<OrderedItemInActiveOrder>
    ): CompleteOrderViewState {
        val completeOrderItemViewStateCollection = orderedItems.map {
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
