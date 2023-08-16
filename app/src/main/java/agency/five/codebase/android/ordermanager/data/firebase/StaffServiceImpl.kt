package agency.five.codebase.android.ordermanager.data.firebase

import agency.five.codebase.android.ordermanager.data.firebase.StaffService
import agency.five.codebase.android.ordermanager.data.firebase.model.DbStaff
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.model.Staff
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_STAFF = "staff"

class StaffServiceImpl(private val fireStore: FirebaseFirestore): StaffService {

    private fun stringToStaffRole(value: String): StaffRoles? {
        return try {
            StaffRoles.valueOf(value)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    private fun mapStaffDocumentToStaff(staff: DocumentSnapshot): DbStaff {
        return DbStaff(
            id = staff.id,
            name = staff.getString("name") ?: "",
            username = staff.getString("username") ?: "",
            password = staff.getString("password") ?: "",
            role = stringToStaffRole(staff.getString("role") ?: "") ?: StaffRoles.NONE,
            createdAt = staff.getTimestamp("createdAt") ?: Timestamp.now()
        )
    }


    /*override fun getAllStaff(): Flow<List<DbStaff>> {
        return callbackFlow {
            val listenerRegistration = fireStore.collection(FIRESTORE_COLLECTION_STAFF)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {
                        val staffCollection = snapshot.documents.map(::mapStaffDocumentToStaff)
                        trySend(staffCollection).isSuccess
                    }
                }
            awaitClose { listenerRegistration.remove() }
        }
    }*/

    override fun getAllStaff(): Flow<List<DbStaff>> {
        return callbackFlow {
            val listenerRegistration = fireStore.collection(FIRESTORE_COLLECTION_STAFF)
                .addSnapshotListener { snapshot, error ->
                    try {
                        if (error != null) {
                            close(error)
                            return@addSnapshotListener
                        }
                        if (snapshot != null) {
                            val staffCollection = snapshot.documents.map(::mapStaffDocumentToStaff)
                            try {
                                trySend(staffCollection)
                            } catch (sendException: Exception) {
                                Log.e("getAllStaff", "Failed to send data", sendException)
                            }
                        }
                    } catch (snapshotException: Exception) {
                        Log.e("getAllStaff", "SnapshotListener error", snapshotException)
                    }
                }
            awaitClose { listenerRegistration.remove() }
        }
    }

    /*override fun getStaffById(staffId: Long): Flow<DbStaff> {
        TODO("Not yet implemented")
    }

    override suspend fun getStaffByCredentials(username: String, password: String): DbStaff? {
        TODO("Not yet implemented")
    }

    override suspend fun addStaff(staff: DbStaff) {
        TODO("Not yet implemented")
    }

    override suspend fun removeStaff(staffId: String) {
        TODO("Not yet implemented")
    }*/
}
