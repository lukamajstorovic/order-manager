package agency.five.codebase.android.ordermanager.data.firebase.model

import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.model.Order
import com.google.firebase.Timestamp

data class DbMenuItem (
    val id: String = "placeholder",
    val name: String,
    val establishmentId: String,
) {
    fun toMenuItem() =
        MenuItem(
            id = id,
            name = name,
            establishmentId = establishmentId,
        )
}
