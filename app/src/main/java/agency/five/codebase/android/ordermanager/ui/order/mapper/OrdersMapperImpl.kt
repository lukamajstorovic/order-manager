package agency.five.codebase.android.ordermanager.ui.order.mapper

import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrdersViewState
import agency.five.codebase.android.ordermanager.ui.order.OrderItemViewState
import agency.five.codebase.android.ordermanager.ui.order.OrdersViewState

class OrdersMapperImpl : OrdersMapper {
    override fun toOrderViewState(orders: List<Order>): OrdersViewState {
        val orderItemViewStateCollection = orders.map { mapOrders(it) }
        return OrdersViewState(
            orderItemViewStateCollection = orderItemViewStateCollection
        )
    }

    override fun toCompletedOrderViewState(orders: List<Order>): CompletedOrdersViewState {
        val completedOrderItemViewStateCollection = orders.map { mapCompletedOrders(it) }
        return CompletedOrdersViewState(
            completedOrderItemViewStateCollection = completedOrderItemViewStateCollection,
        )
    }

    override fun toCompletedOrderItemViewState(order: Order): CompletedOrderItemViewState {
        return mapCompletedOrders(order)
    }

    private fun mapOrders(order: Order): OrderItemViewState {
        return OrderItemViewState(
            id = order.id,
            tableNumber = order.tableNumber,
        )
    }

    private fun mapCompletedOrders(order: Order): CompletedOrderItemViewState {
        return CompletedOrderItemViewState(
            id = order.id,
            tableNumber = order.tableNumber,
            createOrderStaffId = order.createOrderStaffId,
            completeOrderStaffId = order.completeOrderStaffId,
            createdAt = order.createdAt,
        )
    }
}

