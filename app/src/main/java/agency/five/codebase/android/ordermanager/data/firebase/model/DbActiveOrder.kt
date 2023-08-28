package agency.five.codebase.android.ordermanager.data.firebase.model

import agency.five.codebase.android.ordermanager.model.ActiveOrder
import java.math.BigDecimal

data class DbActiveOrder(
    val id: String,
    val tableNumber: String,
    val waiterId: String,
) {
    fun toActiveOrder() =
        ActiveOrder(
            id = id,
            tableNumber = tableNumber,
            waiterId = waiterId,
        )
}
