package agency.five.codebase.android.ordermanager.navigation

import agency.five.codebase.android.ordermanager.R

const val SELECTION_ROUTE = "Selection"
const val CONFIRM_ORDER_ROUTE = "ConfirmOrder"
const val ACTIVE_ORDERS_ROUTE = "ActiveOrders"
const val LOGIN_ROUTE = "Login"

sealed class NavigationItem(
    override val route: String,
    val labelId: Int,
) : OrderManagerDestination(route) {

    object SelectionDestination : NavigationItem(
        route = SELECTION_ROUTE,
        labelId = R.string.add_items
    )

    object ConfirmOrderDestination : NavigationItem(
        route = CONFIRM_ORDER_ROUTE,
        labelId = R.string.confirm_order
    )

    object ActiveOrdersDestination : NavigationItem(
        route = ACTIVE_ORDERS_ROUTE,
        labelId = R.string.active_orders
    )

    object LoginDestination : NavigationItem(
        route = LOGIN_ROUTE,
        labelId = R.string.login
    )
}
