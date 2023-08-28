package agency.five.codebase.android.ordermanager.model

import java.math.BigDecimal

data class ActiveOrder(
    val id: String,
    val tableNumber: String,
    val waiterId: String,
)
