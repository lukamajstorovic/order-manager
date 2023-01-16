package agency.five.codebase.android.ordermanager.model

data class OrderedItemInActiveOrder(
    val id: Int,
    val name: String,
    val amount: Int,
    val activeOrderId: Int,
)
