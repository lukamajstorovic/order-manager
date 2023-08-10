package agency.five.codebase.android.ordermanager.data.repository

import agency.five.codebase.android.ordermanager.model.Staff
import kotlinx.coroutines.flow.Flow

interface StaffRepository {
    fun allStaff(): Flow<List<Staff>>
    fun staffById(staffId: Long): Flow<Staff>
    suspend fun staffByCredentials(username: String, password: String): Staff?
    suspend fun addStaff(staff: Staff)
    suspend fun removeStaff(staffId: Long)
}
