package agency.five.codebase.android.ordermanager.model

import agency.five.codebase.android.ordermanager.data.room.model.DbNotConfirmedOrderItem
import java.math.BigDecimal

data class NotConfirmedOrderItem(
    val id: Int = 0,
    val name: String,
    val amount: Int,
) {
    fun toDbNotConfirmedOrderItem() =
        DbNotConfirmedOrderItem(
            id = id.toLong(),
            name = name,
            amount = amount,
        )
}
