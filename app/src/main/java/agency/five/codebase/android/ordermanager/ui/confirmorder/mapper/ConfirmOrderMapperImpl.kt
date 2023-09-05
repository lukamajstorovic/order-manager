package agency.five.codebase.android.ordermanager.ui.confirmorder.mapper

import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderViewState

class ConfirmOrderMapperImpl : ConfirmOrderMapper {
    override fun toConfirmOrderViewState(toConfirmItems: List<NotConfirmedOrderItem>): ConfirmOrderViewState {
        val confirmOrderItemViewStateCollection = toConfirmItems.map { mapToConfirmItem(it) }
        return ConfirmOrderViewState(
            confirmOrderItemViewStateCollection = confirmOrderItemViewStateCollection
        )
    }

    private fun mapToConfirmItem(orderItem: NotConfirmedOrderItem): ConfirmOrderItemViewState {
        return ConfirmOrderItemViewState(
            id = orderItem.id,
            name = orderItem.name,
            amount = orderItem.amount
        )
    }
}
