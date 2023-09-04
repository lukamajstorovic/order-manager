package agency.five.codebase.android.ordermanager.data.firebase

import agency.five.codebase.android.ordermanager.data.firebase.model.DbMenuItem
import agency.five.codebase.android.ordermanager.data.firebase.model.DbOrder
import agency.five.codebase.android.ordermanager.data.firebase.model.DbOrderItem
import agency.five.codebase.android.ordermanager.data.firebase.model.DbStaff
import agency.five.codebase.android.ordermanager.exceptions.FirestoreException
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_MENU_ITEMS = "menuItems"

class MenuItemServiceImpl(
    private val fireStore: FirebaseFirestore
): MenuItemService {

    private fun mapMenuItemDocumentToDbMenuItem(menuItem: DocumentSnapshot): DbMenuItem {
        return DbMenuItem(
            id = menuItem.id,
            name = menuItem.getString("name") ?: "",
            establishmentId = menuItem.getString("establishmentId") ?: ""
        )
    }

    override fun getMenuItems(establishmentId: String): Flow<List<DbMenuItem>> {
        return callbackFlow {
            val listenerRegistration =
                fireStore
                    .collection(FIRESTORE_COLLECTION_MENU_ITEMS)
                    .whereEqualTo("establishmentId", establishmentId)
                    .addSnapshotListener { snapshot, error ->
                        try {
                            if (error != null) {
                                close(error)
                                return@addSnapshotListener
                            }
                            if (snapshot != null) {
                                val menuItemCollection =
                                    snapshot.documents.map(::mapMenuItemDocumentToDbMenuItem)
                                try {
                                    trySend(menuItemCollection)
                                } catch (sendException: Exception) {
                                    Log.e(
                                        "getAllMenuItems",
                                        "Failed to send data",
                                        sendException
                                    )
                                }
                            }
                        } catch (snapshotException: Exception) {
                            Log.e("getAllMenuItems", "SnapshotListener error", snapshotException)
                        }
                    }
            awaitClose { listenerRegistration.remove() }
        }
    }

    override suspend fun addMenuItem(menuItem: DbMenuItem): Result<Unit> {
        return try {
            fireStore.collection(FIRESTORE_COLLECTION_MENU_ITEMS)
                .add(menuItem)
                .await()
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun updateMenuItem(menuItem: DbMenuItem): Result<Unit> {
        return try {
            fireStore.collection(FIRESTORE_COLLECTION_MENU_ITEMS)
                .document(menuItem.id)
                .set(menuItem, SetOptions.merge())
                .await()
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun removeMenuItem(id: String): Result<Unit> {
        return try {
            fireStore.collection(FIRESTORE_COLLECTION_MENU_ITEMS)
                .document(id)
                .delete()
                .await()
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }
}

