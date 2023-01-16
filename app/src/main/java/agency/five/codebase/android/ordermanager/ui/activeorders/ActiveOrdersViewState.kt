package agency.five.codebase.android.ordermanager.ui.activeorders


data class ActiveOrderItemViewState(
    val id: Int,
    val tableNumber: String,
)

data class ActiveOrdersViewState(
    val activeOrderItemViewStateCollection: List<ActiveOrderItemViewState>
)
