package agency.five.codebase.android.ordermanager.data.firebase.model

import agency.five.codebase.android.ordermanager.model.Order
import com.google.firebase.Timestamp

data class DbOrder(
    val id: String = "placeholder",
    val tableNumber: String,
    val createOrderStaffId: String,
    val completeOrderStaffId: String,
    val active: Boolean,
    val createdAt: Timestamp = Timestamp.now(),
) {
    fun toOrder() =
        Order(
            id = id,
            tableNumber = tableNumber,
            createOrderStaffId  = createOrderStaffId,
            completeOrderStaffId = completeOrderStaffId,
            active = active,
            createdAt = createdAt,
        )
}
