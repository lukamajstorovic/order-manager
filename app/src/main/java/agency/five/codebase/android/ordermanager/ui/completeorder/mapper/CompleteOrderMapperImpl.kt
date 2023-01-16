package agency.five.codebase.android.ordermanager.ui.completeorder.mapper

import agency.five.codebase.android.ordermanager.model.OrderedItemInActiveOrder
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewState

class CompleteOrderMapperImpl : CompleteOrderMapper {

    override fun toCompleteOrderViewState(orderedItems: List<OrderedItemInActiveOrder>): CompleteOrderViewState {
        val completeOrderItemViewStateCollection = orderedItems.map {
            CompleteOrderItemViewState(
                id = it.id,
                name = it.name,
                amount = it.amount
            )
        }
        if (orderedItems.isEmpty()) {
            return CompleteOrderViewState(
                //orderId = orderedItems[0].activeOrderId,
                orderId = 1,
                completeOrderItemViewStateCollection = completeOrderItemViewStateCollection
            )
        } else {
            return CompleteOrderViewState(
                orderId = orderedItems[0].activeOrderId,
                completeOrderItemViewStateCollection = completeOrderItemViewStateCollection
            )
        }
    }
}
