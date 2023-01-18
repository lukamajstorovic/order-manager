package agency.five.codebase.android.ordermanager.ui.completeorder.mapper

import agency.five.codebase.android.ordermanager.model.OrderedItemInActiveOrder
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewState

interface CompleteOrderMapper {
    fun toCompleteOrderViewState(orderId: Int,orderedItems: List<OrderedItemInActiveOrder>): CompleteOrderViewState
}
