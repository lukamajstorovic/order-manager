package agency.five.codebase.android.ordermanager.data.room.model

import agency.five.codebase.android.ordermanager.model.NotConfirmedOrderItem
import agency.five.codebase.android.ordermanager.model.OrderItem
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orderItems")
data class DbNotConfirmedOrderItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val amount: Int,
) {
    fun toNotConfirmedOrderItem() =
        NotConfirmedOrderItem(
            id = id.toInt(),
            name = name,
            amount = amount,
        )

    fun toOrderItem(orderId: String) =
        OrderItem(
            orderId = orderId,
            name = name,
            amount = amount,
        )
}

