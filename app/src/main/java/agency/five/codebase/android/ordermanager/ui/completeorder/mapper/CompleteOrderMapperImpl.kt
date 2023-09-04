package agency.five.codebase.android.ordermanager.ui.completeorder.mapper

import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.model.OrderItem
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewState
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrdersViewState

class CompleteOrderMapperImpl : CompleteOrderMapper {
    override fun toCompleteOrderViewState(
        orderId: String,
        orderItems: List<OrderItem>
    ): CompleteOrderViewState {
        val completeOrderItemViewStateCollection = orderItems.map {
            mapOrderItems(it)
        }
        return CompleteOrderViewState(
            orderId = orderId,
            completeOrderItemViewStateCollection = completeOrderItemViewStateCollection
        )
    }

    override fun toCompletedOrderViewState(
        order: Order,
        orderItems: List<OrderItem>
    ): CompletedOrdersViewState {
        val completedOrderItemViewStateCollection = orderItems.map { mapOrderItems(it) }
        return CompletedOrdersViewState(
            id = order.id,
            tableNumber = order.tableNumber,
            createOrderStaffId = order.createOrderStaffId,
            completeOrderStaffId = order.completeOrderStaffId,
            createdAt = order.createdAt,
            completedOrderItemViewStateCollection = completedOrderItemViewStateCollection,
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
