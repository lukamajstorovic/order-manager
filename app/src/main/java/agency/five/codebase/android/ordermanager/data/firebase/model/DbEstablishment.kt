package agency.five.codebase.android.ordermanager.data.firebase.model

import agency.five.codebase.android.ordermanager.model.Establishment
import com.google.firebase.firestore.DocumentId

data class DbEstablishment(
    @DocumentId
    val id: String = "placeholder",
    val name: String,
) {
    fun toEstablishment() =
        Establishment(
            id = id,
            name = name,
        )
}

