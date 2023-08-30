package agency.five.codebase.android.ordermanager.ui.order.mapper

import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.ui.order.OrderItemViewState
import agency.five.codebase.android.ordermanager.ui.order.OrdersViewState

class OrdersMapperImpl : OrdersMapper {
    override fun toOrderViewState(orders: List<Order>): OrdersViewState {
        val orderItemViewStateCollection = orders.map { mapOrders(it) }
        return OrdersViewState(
            orderItemViewStateCollection = orderItemViewStateCollection
        )
    }

    private fun mapOrders(order: Order): OrderItemViewState {
        return OrderItemViewState(
            id = order.id,
            tableNumber = order.tableNumber,
        )
    }
}

