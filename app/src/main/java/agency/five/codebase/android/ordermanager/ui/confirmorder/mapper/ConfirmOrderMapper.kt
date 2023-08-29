package agency.five.codebase.android.ordermanager.ui.confirmorder.mapper

import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderViewState

interface ConfirmOrderMapper {
    fun toConfirmOrderViewState(toConfirmItems: List<NotConfirmedOrderItem>): ConfirmOrderViewState
}
