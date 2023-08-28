package agency.five.codebase.android.ordermanager.data.firebase.model

import agency.five.codebase.android.ordermanager.model.OrderedItemInActiveOrder
import java.math.BigDecimal

data class DbOrderedItemInActiveOrder(
    val id: String,
    val activeOrderId: String,
    val name: String,
    val amount: Int,
    val price: BigDecimal,
) {
    fun toOrderedItemInActiveOrder() =
        OrderedItemInActiveOrder(
            id = id,
            activeOrderId = activeOrderId,
            name = name,
            amount = amount,
            price = price,
        )
}
