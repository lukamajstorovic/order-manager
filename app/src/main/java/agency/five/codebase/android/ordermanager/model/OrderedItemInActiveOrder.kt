package agency.five.codebase.android.ordermanager.model

import java.math.BigDecimal


data class OrderedItemInActiveOrder(
    val id: String,
    val activeOrderId: String,
    val name: String,
    val amount: Int,
    val price: BigDecimal,
)
