package agency.five.codebase.android.ordermanager.ui.order.mapper

import agency.five.codebase.android.ordermanager.model.Order
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrderViewStateItemCollectionViewState
import agency.five.codebase.android.ordermanager.ui.order.OrdersViewState

interface OrdersMapper {
    fun toOrderViewState(orders: List<Order>): OrdersViewState
    fun toCompletedOrdersMinimalInfoViewState(
        orderItems: List<Order>
    ): CompletedOrderViewStateItemCollectionViewState
}
