package agency.five.codebase.android.ordermanager.navigation

const val INDIVIDUAL_STAFF_ROUTE = "IndividualStaff"
const val INDIVIDUAL_STAFF_KEY = "staffId"
const val INDIVIDUAL_STAFF_ROUTE_WITH_PARAMS = "$INDIVIDUAL_STAFF_ROUTE/{$INDIVIDUAL_STAFF_KEY}"

data object IndividualStaffDestination : OrderManagerDestination(INDIVIDUAL_STAFF_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(staffId: String): String = "$INDIVIDUAL_STAFF_ROUTE/$staffId"
}
