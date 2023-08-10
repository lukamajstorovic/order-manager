package agency.five.codebase.android.ordermanager.ui.activeorders.mapper

import agency.five.codebase.android.ordermanager.model.ActiveOrder
import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrdersViewState

class ActiveOrdersMapperImpl : ActiveOrdersMapper {
    override fun toActiveOrderViewState(activeOrders: List<ActiveOrder>): ActiveOrdersViewState {
        val activeOrderItemViewStateCollection = activeOrders.map { mapActiveOrders(it) }
        return ActiveOrdersViewState(
            activeOrderItemViewStateCollection = activeOrderItemViewStateCollection
        )
    }

    private fun mapActiveOrders(activeOrder: ActiveOrder): ActiveOrderItemViewState {
        return ActiveOrderItemViewState(
            id = activeOrder.id,
            tableNumber = activeOrder.tableNumber,
        )
    }
}

