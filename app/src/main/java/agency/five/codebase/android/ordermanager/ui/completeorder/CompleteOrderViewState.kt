package agency.five.codebase.android.ordermanager.ui.completeorder

data class CompleteOrderItemViewState(
    val id: Int,
    val name: String,
    val amount: Int,
)

data class CompleteOrderViewState(
    val orderId: Int,
    val completeOrderItemViewStateCollection: List<CompleteOrderItemViewState>
)
