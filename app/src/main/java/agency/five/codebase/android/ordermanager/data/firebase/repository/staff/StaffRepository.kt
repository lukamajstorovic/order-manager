package agency.five.codebase.android.ordermanager.data.firebase.repository.staff

import agency.five.codebase.android.ordermanager.data.firebase.model.DbStaff
import kotlinx.coroutines.flow.Flow

interface StaffRepository {
    fun getAllStaff(): Flow<List<DbStaff>>
    suspend fun getStaffById(staffId: String): Result<DbStaff>
    suspend fun getStaffByCredentials(username: String, password: String): DbStaff?
    suspend fun getStaffByEstablishment(establishmentId: String): Flow<List<DbStaff>>
    suspend fun getStaffUsernameExists(username: String): Result<Boolean>
    suspend fun getApprovedStaff(establishmentId: String): Flow<List<DbStaff>>
    suspend fun getNotApprovedStaff(establishmentId: String): Flow<List<DbStaff>>
    suspend fun addStaff(staff: DbStaff): Result<Unit>
    suspend fun updateStaff(staff: DbStaff): Result<Unit>
    suspend fun removeStaff(staffId: String)
}
