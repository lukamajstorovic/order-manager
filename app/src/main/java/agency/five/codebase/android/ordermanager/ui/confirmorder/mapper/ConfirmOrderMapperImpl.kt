package agency.five.codebase.android.ordermanager.ui.confirmorder.mapper

import agency.five.codebase.android.ordermanager.model.OrderedItem
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderViewState

class ConfirmOrderMapperImpl : ConfirmOrderMapper {
    override fun toConfirmOrderViewState(toConfirmItems: List<OrderedItem>): ConfirmOrderViewState {
        val confirmOrderItemViewStateCollection = toConfirmItems.map { mapToConfirmItem(it) }
        return ConfirmOrderViewState(
            confirmOrderItemViewStateCollection = confirmOrderItemViewStateCollection
        )
    }

    private fun mapToConfirmItem(orderedItem: OrderedItem): ConfirmOrderItemViewState {
        return ConfirmOrderItemViewState(
            id = orderedItem.id,
            name = orderedItem.name,
            amount = orderedItem.amount
        )
    }
}
