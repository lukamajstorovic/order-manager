package agency.five.codebase.android.ordermanager.data.repository

import agency.five.codebase.android.ordermanager.model.Staff
import kotlinx.coroutines.flow.Flow

interface StaffRepository {
    suspend fun allStaff(): Flow<List<Staff>>
    suspend fun staffById(staffId: Long): Flow<Staff>
    suspend fun addStaff(staff: Staff)
    suspend fun removeStaff(staffId: Long)
}
