package agency.five.codebase.android.ordermanager.navigation

import agency.five.codebase.android.ordermanager.R

const val SELECTION_ROUTE = "Selection"
const val CONFIRM_ORDER_ROUTE = "ConfirmOrder"
const val ACTIVE_ORDERS_ROUTE = "Orders"
const val COMPLETED_ORDERS_ROUTE = "CompletedOrders"
const val LOGIN_ROUTE = "Login"
const val APPROVED_STAFF_ROUTE = "ApprovedStaff"
const val NOT_APPROVED_STAFF_ROUTE = "NotApprovedStaff"
const val REGISTER_ROUTE = "RegisterStaff"

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

    data object OrdersDestination : NavigationItem(
        route = ACTIVE_ORDERS_ROUTE,
        labelId = R.string.active_orders
    )

    data object CompletedOrdersDestination : NavigationItem(
        route = COMPLETED_ORDERS_ROUTE,
        labelId = R.string.completed_orders,
    )

    data object LoginDestination : NavigationItem(
        route = LOGIN_ROUTE,
        labelId = R.string.login
    )

    data object ApprovedStaffDestination : NavigationItem(
        route = APPROVED_STAFF_ROUTE,
        labelId = R.string.approved_staff
    )

    data object NotApprovedStaffDestination : NavigationItem(
        route = NOT_APPROVED_STAFF_ROUTE,
        labelId = R.string.not_approved_staff
    )

    data object RegisterStaffDestination : NavigationItem(
        route = REGISTER_ROUTE,
        labelId = R.string.register_staff
    )
}
