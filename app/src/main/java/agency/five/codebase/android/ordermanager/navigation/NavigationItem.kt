package agency.five.codebase.android.ordermanager.navigation

import agency.five.codebase.android.ordermanager.R

const val SELECTION_ROUTE = "Selection"
const val CONFIRM_ORDER_ROUTE = "ConfirmOrder"
const val ACTIVE_ORDERS_ROUTE = "ActiveOrders"
const val LOGIN_ROUTE = "Login"
const val STAFF_ROUTE = "Staff"

sealed class NavigationItem(
    override val route: String,
    val labelId: Int,
) : OrderManagerDestination(route) {

    data object SelectionDestination : NavigationItem(
        route = SELECTION_ROUTE,
        labelId = R.string.add_items
    )

    data object ConfirmOrderDestination : NavigationItem(
        route = CONFIRM_ORDER_ROUTE,
        labelId = R.string.confirm_order
    )

    data object ActiveOrdersDestination : NavigationItem(
        route = ACTIVE_ORDERS_ROUTE,
        labelId = R.string.active_orders
    )

    data object LoginDestination : NavigationItem(
        route = LOGIN_ROUTE,
        labelId = R.string.login
    )

    data object StaffDestination : NavigationItem(
        route = STAFF_ROUTE,
        labelId = R.string.staff
    )
}
