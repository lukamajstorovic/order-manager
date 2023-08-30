package agency.five.codebase.android.ordermanager.data.firebase.model

import agency.five.codebase.android.ordermanager.model.OrderItem
import java.math.BigDecimal

data class DbOrderItem(
    val id: String = "placeholder",
    val orderId: String,
    val name: String,
    val amount: Int,
) {
    fun toOrderItem() =
        OrderItem(
            id = id,
            orderId = orderId,
            name = name,
            amount = amount,
        )
}
