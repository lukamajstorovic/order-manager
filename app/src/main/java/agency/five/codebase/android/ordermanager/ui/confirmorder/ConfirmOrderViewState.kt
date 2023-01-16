package agency.five.codebase.android.ordermanager.ui.confirmorder

data class ConfirmOrderItemViewState(
    val id: Int,
    val name: String,
    val amount: Int,
)

data class ConfirmOrderViewState(
    val confirmOrderItemViewStateCollection: List<ConfirmOrderItemViewState>
)
