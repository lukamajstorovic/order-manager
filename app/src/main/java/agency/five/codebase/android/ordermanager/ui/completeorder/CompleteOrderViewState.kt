package agency.five.codebase.android.ordermanager.ui.completeorder

data class CompleteOrderItemViewState(
    val id: String,
    val name: String,
    val amount: Int,
)

data class CompleteOrderViewState(
    val orderId: String,
    val completeOrderItemViewStateCollection: List<CompleteOrderItemViewState>
)
