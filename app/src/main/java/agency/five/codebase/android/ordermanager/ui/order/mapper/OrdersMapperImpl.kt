package agency.five.codebase.android.ordermanager.ui.order.mapper

import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.model.OrderItem
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.order.CompleteOrderViewStateItem
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrderViewStateItemCollectionViewState
import agency.five.codebase.android.ordermanager.ui.order.OrderItemViewState
import agency.five.codebase.android.ordermanager.ui.order.OrdersViewState

class OrdersMapperImpl : OrdersMapper {
    override fun toOrderViewState(orders: List<Order>): OrdersViewState {
        val orderItemViewStateCollection = orders.map { mapOrders(it) }
        return OrdersViewState(
            orderItemViewStateCollection = orderItemViewStateCollection
        )
    }

    override fun toCompletedOrdersMinimalInfoViewState(
        orderItems: List<Order>
    ): CompletedOrderViewStateItemCollectionViewState {
        val completedOrderViewStateItemCollection = orderItems.map { mapCompletedOrders(it) }
        return CompletedOrderViewStateItemCollectionViewState(
            completedOrderViewStateItemCollection = completedOrderViewStateItemCollection,
        )
    }

    private fun mapOrders(order: Order): OrderItemViewState {
        return OrderItemViewState(
            id = order.id,
            tableNumber = order.tableNumber,
        )
    }

    private fun mapCompletedOrders(order: Order): CompleteOrderViewStateItem {
        return CompleteOrderViewStateItem(
            id = order.id,
            tableNumber = order.tableNumber,
            createdAt = order.createdAt,
        )
    }

    private fun mapOrderItems(orderItem: OrderItem): CompleteOrderItemViewState {
        return CompleteOrderItemViewState(
            id = orderItem.id,
            name = orderItem.name,
            amount = orderItem.amount
        )
    }
}

