package agency.five.codebase.android.ordermanager.data.repository.staff

import agency.five.codebase.android.ordermanager.model.Staff
import kotlinx.coroutines.flow.Flow

interface StaffRepository {
    fun allStaff(): Flow<List<Staff>>
    suspend fun staffById(staffId: String): Staff?
    suspend fun staffByCredentials(username: String, password: String): Staff?
    suspend fun addStaff(staff: Staff)
    suspend fun removeStaff(staffId: String)
}
