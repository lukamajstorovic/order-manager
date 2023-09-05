package agency.five.codebase.android.ordermanager.data.firebase

import agency.five.codebase.android.ordermanager.data.firebase.model.DbEstablishment
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_ESTABLISHMENTS = "establishments"

class EstablishmentServiceImpl(private val fireStore: FirebaseFirestore) : EstablishmentService {

    private fun mapEstablishmentDocumentToDbEstablishment(establishment: DocumentSnapshot): DbEstablishment {
        return DbEstablishment(
            id = establishment.id,
            name = establishment.getString("name") ?: ""
        )
    }

    override fun getAllEstablishments(): Flow<List<DbEstablishment>> {
        return callbackFlow {
            val listenerRegistration = fireStore.collection(FIRESTORE_COLLECTION_ESTABLISHMENTS)
                .addSnapshotListener { snapshot, error ->
                    try {
                        if (error != null) {
                            close(error)
                            return@addSnapshotListener
                        }
                        if (snapshot != null) {
                            val establishmentCollection =
                                snapshot.documents.map(::mapEstablishmentDocumentToDbEstablishment)
                            try {
                                trySend(establishmentCollection)
                            } catch (sendException: Exception) {
                                Log.e("getAllEstablishments", "Failed to send data", sendException)
                            }
                        }
                    } catch (snapshotException: Exception) {
                        Log.e("getAllEstablishments", "SnapshotListener error", snapshotException)
                    }
                }
            awaitClose { listenerRegistration.remove() }
        }
    }

    override suspend fun getEstablishmentById(establishmentId: String): DbEstablishment? {
        val establishmentDocument = fireStore.collection(FIRESTORE_COLLECTION_STAFF)
            .document(establishmentId)
            .get()
            .await()

        if (!establishmentDocument.exists()) {
            return mapEstablishmentDocumentToDbEstablishment(establishmentDocument)
        }
        return null
    }

    override suspend fun addEstablishment(establishment: DbEstablishment) {
        fireStore.collection(FIRESTORE_COLLECTION_STAFF)
            .add(establishment)
            .addOnFailureListener {
                Log.d("Something went wrong: ", it.message.toString())
            }
    }
}
