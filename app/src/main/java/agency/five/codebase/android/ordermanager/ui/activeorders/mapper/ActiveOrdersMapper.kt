package agency.five.codebase.android.ordermanager.ui.activeorders.mapper

import agency.five.codebase.android.ordermanager.model.ActiveOrder
import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrdersViewState

interface ActiveOrdersMapper {
    fun toActiveOrderViewState(activeOrders: List<ActiveOrder>): ActiveOrdersViewState
}
