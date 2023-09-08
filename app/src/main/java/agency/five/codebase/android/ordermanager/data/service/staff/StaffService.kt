package agency.five.codebase.android.ordermanager.data.service.staff

import agency.five.codebase.android.ordermanager.model.Staff
import kotlinx.coroutines.flow.Flow

interface StaffService {
    fun allStaff(): Flow<List<Staff>>
    suspend fun staffById(staffId: String): Result<Staff>
    suspend fun staffByCredentials(username: String, password: String): Staff?
    suspend fun staffByEstablishment(establishmentId: String): Flow<List<Staff>>
    suspend fun staffUsernameExists(username: String): Result<Boolean>
    suspend fun getApprovedStaff(establishmentId: String): Flow<List<Staff>>
    suspend fun getNotApprovedStaff(establishmentId: String): Flow<List<Staff>>
    suspend fun addStaff(staff: Staff): Result<Unit>
    suspend fun updateStaff(staff: Staff): Result<Unit>
    suspend fun removeStaff(staffId: String)
}
