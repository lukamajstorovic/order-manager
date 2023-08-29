package agency.five.codebase.android.ordermanager.model

import agency.five.codebase.android.ordermanager.data.firebase.model.DbOrder
import com.google.firebase.Timestamp

data class Order(
    val id: String = "placeholder",
    val tableNumber: String,
    val createOrderStaffId: String,
    val completeOrderStaffId: String,
    val active: Boolean,
    val createdAt: Timestamp = Timestamp.now(),
) {
    fun toDbOrder() =
        DbOrder(
            id = id,
            tableNumber = tableNumber,
            createOrderStaffId  = createOrderStaffId,
            completeOrderStaffId = completeOrderStaffId,
            active = active,
            createdAt = createdAt,
        )
}
