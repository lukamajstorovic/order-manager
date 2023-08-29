package agency.five.codebase.android.ordermanager.model

import agency.five.codebase.android.ordermanager.data.firebase.model.DbOrderItem
import java.math.BigDecimal

data class OrderItem(
    val id: String = "placeholder",
    val orderId: String,
    val name: String,
    val amount: Int,
) {
    fun toDbOrderItem() =
        DbOrderItem(
            id = id,
            orderId = orderId,
            name = name,
            amount = amount,
        )
    fun toDbOrderItem(orderId: String) =
        DbOrderItem(
            id = id,
            orderId = orderId,
            name = name,
            amount = amount,
        )
}
