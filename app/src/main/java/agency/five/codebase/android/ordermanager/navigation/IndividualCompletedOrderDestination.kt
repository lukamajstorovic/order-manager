package agency.five.codebase.android.ordermanager.navigation

const val INDIVIDUAL_COMPLETED_ORDER_ROUTE = "IndividualCompletedOrder"
const val INDIVIDUAL_COMPLETED_ORDER_KEY = "orderId"
const val INDIVIDUAL_COMPLETED_ORDER_ROUTE_WITH_PARAMS = "$INDIVIDUAL_COMPLETED_ORDER_ROUTE/{$INDIVIDUAL_COMPLETED_ORDER_KEY}"

data object IndividualCompletedOrderDestination : OrderManagerDestination(INDIVIDUAL_COMPLETED_ORDER_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(orderId: String): String = "$INDIVIDUAL_COMPLETED_ORDER_ROUTE/$orderId"
}
