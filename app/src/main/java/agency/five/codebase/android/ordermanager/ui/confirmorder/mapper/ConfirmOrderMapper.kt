package agency.five.codebase.android.ordermanager.ui.confirmorder.mapper

import agency.five.codebase.android.ordermanager.model.OrderedItem
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderViewState

interface ConfirmOrderMapper {
    fun toConfirmOrderViewState(toConfirmItems: List<OrderedItem>): ConfirmOrderViewState
}
