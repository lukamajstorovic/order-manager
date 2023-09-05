package agency.five.codebase.android.ordermanager.data.firebase

import agency.five.codebase.android.ordermanager.data.firebase.model.DbOrder
import agency.five.codebase.android.ordermanager.data.firebase.model.DbOrderItem
import agency.five.codebase.android.ordermanager.exceptions.FirestoreException
import agency.five.codebase.android.ordermanager.exceptions.OrderNotFoundException
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

const val FIRESTORE_COLLECTION_ORDERS = "orders"
const val FIRESTORE_COLLECTION_ORDER_ITEMS = "orderItems"

class OrderServiceImpl(private val fireStore: FirebaseFirestore) : OrderService {
    private fun mapOrderDocumentToDbOrder(order: DocumentSnapshot): DbOrder {
        return DbOrder(
            id = order.id,
            establishmentId = order.getString("establishmentId") ?: "",
            tableNumber = order.getString("tableNumber") ?: "",
            createOrderStaffId = order.getString("createOrderStaffId") ?: "",
            completeOrderStaffId = order.getString("completeOrderStaffId") ?: "",
            active = order.getBoolean("active") ?: false,
            createdAt = order.getTimestamp("createdAt") ?: Timestamp.now()
        )
    }

    private fun mapOrderItemDocumentToDbOrderItem(orderItem: DocumentSnapshot): DbOrderItem {
        return DbOrderItem(
            id = orderItem.id,
            orderId = orderItem.getString("orderId") ?: "",
            name = orderItem.getString("name") ?: "",
            amount = orderItem.getLong("amount")?.toInt() ?: 0,
        )
    }

    override fun getAllActiveOrders(establishmentId: String): Flow<List<DbOrder>> {
        return callbackFlow {
            val listenerRegistration =
                fireStore
                    .collection(FIRESTORE_COLLECTION_ORDERS)
                    .whereEqualTo("establishmentId", establishmentId)
                    .whereEqualTo("active", true)
                    .addSnapshotListener { snapshot, error ->
                        try {
                            if (error != null) {
                                close(error)
                                return@addSnapshotListener
                            }
                            if (snapshot != null) {
                                val orderCollection =
                                    snapshot.documents.map(::mapOrderDocumentToDbOrder)
                                try {
                                    trySend(orderCollection)
                                } catch (sendException: Exception) {
                                    Log.e(
                                        "getAllActiveOrders",
                                        "Failed to send data",
                                        sendException
                                    )
                                }
                            }
                        } catch (snapshotException: Exception) {
                            Log.e("getAllActiveOrders", "SnapshotListener error", snapshotException)
                        }
                    }
            awaitClose { listenerRegistration.remove() }
        }
    }

    override fun getAllCompletedOrders(establishmentId: String): Flow<List<DbOrder>> {
        return callbackFlow {
            val listenerRegistration =
                fireStore
                    .collection(FIRESTORE_COLLECTION_ORDERS)
                    .whereEqualTo("establishmentId", establishmentId)
                    .whereEqualTo("active", false)
                    .orderBy("createdAt", Query.Direction.DESCENDING)
                    .addSnapshotListener { snapshot, error ->
                        try {
                            if (error != null) {
                                close(error)
                                return@addSnapshotListener
                            }
                            if (snapshot != null) {
                                val orderCollection =
                                    snapshot.documents.map(::mapOrderDocumentToDbOrder)
                                try {
                                    trySend(orderCollection)
                                } catch (sendException: Exception) {
                                    Log.e(
                                        "getAllCompletedOrders",
                                        "Failed to send data",
                                        sendException
                                    )
                                }
                            }
                        } catch (snapshotException: Exception) {
                            Log.e(
                                "getAllCompletedOrders",
                                "SnapshotListener error",
                                snapshotException
                            )
                        }
                    }
            awaitClose { listenerRegistration.remove() }
        }
    }

    override fun getOrderItems(orderId: String): Flow<List<DbOrderItem>> {
        return callbackFlow {
            val listenerRegistration =
                fireStore
                    .collection(FIRESTORE_COLLECTION_ORDER_ITEMS)
                    .whereEqualTo("orderId", orderId)
                    .addSnapshotListener { snapshot, error ->
                        try {
                            if (error != null) {
                                close(error)
                                return@addSnapshotListener
                            }
                            if (snapshot != null) {
                                val orderItemCollection =
                                    snapshot.documents.map(::mapOrderItemDocumentToDbOrderItem)
                                try {
                                    trySend(orderItemCollection)
                                } catch (sendException: Exception) {
                                    Log.e("getOrderItems", "Failed to send data", sendException)
                                }
                            }
                        } catch (snapshotException: Exception) {
                            Log.e("getOrderItems", "SnapshotListener error", snapshotException)
                        }
                    }
            awaitClose { listenerRegistration.remove() }
        }
    }

    override suspend fun getOrderById(id: String): Result<DbOrder> {
        return try {
            val orderDocument = fireStore.collection(FIRESTORE_COLLECTION_ORDERS)
                .document(id)
                .get()
                .await()
            if (orderDocument.exists()) {
                Result.success(mapOrderDocumentToDbOrder(orderDocument))
            } else {
                Result.failure(OrderNotFoundException())
            }
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun addOrder(order: DbOrder): Result<String> {
        return try {
            val documentReference = fireStore.collection(FIRESTORE_COLLECTION_ORDERS)
                .add(order)
                .await()
            Result.success(documentReference.id)
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun addOrderItem(orderItem: DbOrderItem): Result<Unit> {
        return try {
            fireStore.collection(FIRESTORE_COLLECTION_ORDER_ITEMS)
                .add(orderItem)
                .await()
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun updateOrder(order: DbOrder): Result<Unit> {
        return try {
            fireStore.collection(FIRESTORE_COLLECTION_ORDERS)
                .document(order.id)
                .set(order, SetOptions.merge())
                .await()
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }

    override suspend fun updateOrderItem(orderItem: DbOrderItem): Result<Unit> {
        return try {
            fireStore.collection(FIRESTORE_COLLECTION_ORDER_ITEMS)
                .document(orderItem.id)
                .set(orderItem, SetOptions.merge())
                .await()
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(FirestoreException(exception))
        }
    }
}
