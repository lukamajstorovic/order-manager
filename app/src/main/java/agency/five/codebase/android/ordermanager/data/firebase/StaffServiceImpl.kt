package agency.five.codebase.android.ordermanager.data.firebase

import agency.five.codebase.android.ordermanager.data.firebase.model.DbStaff
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.exceptions.FirestoreException
import agency.five.codebase.android.ordermanager.exceptions.StaffNotFoundException
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_STAFF = "staff"

class StaffServiceImpl(private val fireStore: FirebaseFirestore) : StaffService {

    private fun stringToStaffRole(value: String): StaffRoles? {
        return try {
            StaffRoles.valueOf(value)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    private fun mapStaffDocumentToDbStaff(staff: DocumentSnapshot): DbStaff {
        return DbStaff(
            id = staff.id,
            name = staff.getString("name") ?: "",
            username = staff.getString("username") ?: "",
            password = staff.getString("password") ?: "",
            role = stringToStaffRole(staff.getString("role") ?: "") ?: StaffRoles.NONE,
            establishmentId = staff.getString("establishmentId") ?: "",
            approved = staff.getBoolean("approved") ?: false,
            createdAt = staff.getTimestamp("createdAt") ?: Timestamp.now()
        )
    }

    override suspend fun getStaffByEstablishment(establishmentId: String): Flow<List<DbStaff>> {
        return callbackFlow {
            val listenerRegistration =
                fireStore
                    .collection(FIRESTORE_COLLECTION_STAFF)
                    .whereEqualTo("establishmentId", establishmentId)
                    .orderBy("role", Query.Direction.ASCENDING)
                    .addSnapshotListener { snapshot, error ->
                        try {
                            if (error != null) {
                                close(error)
                                return@addSnapshotListener
                            }
                            if (snapshot != null) {
                                val staffCollection =
                                    snapshot.documents.map(::mapStaffDocumentToDbStaff)
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

    override suspend fun getStaffUsernameExists(username: String): Result<Boolean> {
        try {
            val staffDocument = fireStore
                .collection(FIRESTORE_COLLECTION_STAFF)
                .whereEqualTo("username", username)
                .get()
                .await()
            return Result.success(!staffDocument.isEmpty)
        } catch (exception: Exception) {
            return Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun getApprovedStaff(establishmentId: String): Flow<List<DbStaff>> {
        return callbackFlow {
            val listenerRegistration =
                fireStore
                    .collection(FIRESTORE_COLLECTION_STAFF)
                    .whereEqualTo("establishmentId", establishmentId)
                    .whereEqualTo("approved", true)
                    .orderBy("role", Query.Direction.ASCENDING)
                    .addSnapshotListener { snapshot, error ->
                        try {
                            if (error != null) {
                                close(error)
                                return@addSnapshotListener
                            }
                            if (snapshot != null) {
                                val staffCollection =
                                    snapshot.documents.map(::mapStaffDocumentToDbStaff)
                                try {
                                    println(staffCollection)
                                    trySend(staffCollection)
                                } catch (sendException: Exception) {
                                    println(sendException)
                                    Log.e("getAllStaff", "Failed to send data", sendException)
                                }
                            }
                        } catch (snapshotException: Exception) {
                            println(snapshotException)
                            Log.e("getAllStaff", "SnapshotListener error", snapshotException)
                        }
                    }
            awaitClose { listenerRegistration.remove() }
        }
    }

    override suspend fun getNotApprovedStaff(establishmentId: String): Flow<List<DbStaff>> {
        return callbackFlow {
            val listenerRegistration =
                fireStore
                    .collection(FIRESTORE_COLLECTION_STAFF)
                    .whereEqualTo("establishmentId", establishmentId)
                    .whereEqualTo("approved", false)
                    .orderBy("role", Query.Direction.ASCENDING)
                    .addSnapshotListener { snapshot, error ->
                        try {
                            if (error != null) {
                                close(error)
                                return@addSnapshotListener
                            }
                            if (snapshot != null) {
                                val staffCollection =
                                    snapshot.documents.map(::mapStaffDocumentToDbStaff)
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
                            val staffCollection =
                                snapshot.documents.map(::mapStaffDocumentToDbStaff)
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

    override suspend fun getStaffById(id: String): Result<DbStaff> {
        return try {
            val staffDocument = fireStore.collection(FIRESTORE_COLLECTION_STAFF)
                .document(id)
                .get()
                .await()
            if (staffDocument.exists()) {
                Result.success(mapStaffDocumentToDbStaff(staffDocument))
            } else {
                Result.failure(StaffNotFoundException())
            }
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun getStaffByCredentials(username: String, password: String): DbStaff? {
        val query = fireStore.collection(FIRESTORE_COLLECTION_STAFF)
            .whereEqualTo("username", username)
            .whereEqualTo("password", password)
            .limit(1)

        val querySnapshot = query.get().await()
        if (!querySnapshot.isEmpty) {
            val staffDocument = querySnapshot.documents[0]
            return mapStaffDocumentToDbStaff(staffDocument)
        }
        return null
    }

    override suspend fun addStaff(staff: DbStaff): Result<Unit> {
        return try {
            fireStore.collection(FIRESTORE_COLLECTION_STAFF)
                .add(staff)
                .await()
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun updateStaff(staff: DbStaff): Result<Unit> {
        return try {
            fireStore.collection(FIRESTORE_COLLECTION_STAFF)
                .document(staff.id)
                .set(staff, SetOptions.merge())
                .await()
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun removeStaff(id: String) {
        fireStore.collection(FIRESTORE_COLLECTION_STAFF)
            .document(id)
            .delete()
            .await()
    }
}
