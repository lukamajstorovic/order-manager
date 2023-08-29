package agency.five.codebase.android.ordermanager.data.room.model

import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.OrderItem
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "orderItems",)
data class DbNotConfirmedOrderItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val amount: Int,
    val price: String,
) {
    fun toNotConfirmedOrderItem() =
        NotConfirmedOrderItem(
            id = id.toInt(),
            name = name,
            amount = amount,
            price = price,
        )
    fun toOrderItem(orderId: String) =
        OrderItem(
            orderId = orderId,
            name = name,
            amount = amount,
            price = BigDecimal(price),
        )
}

