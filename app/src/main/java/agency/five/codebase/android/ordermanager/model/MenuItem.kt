package agency.five.codebase.android.ordermanager.model

import agency.five.codebase.android.ordermanager.data.firebase.model.DbMenuItem

data class MenuItem(
    val id: String,
    val name: String,
    val establishmentId: String,
) {
    fun toDbMenuItem() =
        DbMenuItem(
            id = id,
            name = name,
            establishmentId = establishmentId,
        )
}
