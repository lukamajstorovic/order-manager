package agency.five.codebase.android.ordermanager.ui.order.mapper

import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrdersViewState
import agency.five.codebase.android.ordermanager.ui.order.OrdersViewState

interface OrdersMapper {
    fun toOrderViewState(orders: List<Order>): OrdersViewState
    fun toCompletedOrderViewState(orders: List<Order>): CompletedOrdersViewState
    fun toCompletedOrderItemViewState(order: Order): CompletedOrderItemViewState
}
