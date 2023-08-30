package agency.five.codebase.android.ordermanager.navigation

const val COMPLETE_ORDER_ROUTE = "CompleteOrder"
const val ORDER_KEY = "orderId"
const val COMPLETE_ORDER_ROUTE_WITH_PARAMS = "$COMPLETE_ORDER_ROUTE/{$ORDER_KEY}"

data object CompleteOrderDestination : OrderManagerDestination(COMPLETE_ORDER_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(orderId: String): String = "$COMPLETE_ORDER_ROUTE/$orderId"
}
