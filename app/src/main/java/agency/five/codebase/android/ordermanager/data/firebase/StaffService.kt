package agency.five.codebase.android.ordermanager.data.firebase

import agency.five.codebase.android.ordermanager.data.firebase.model.DbStaff
import kotlinx.coroutines.flow.Flow

interface StaffService {
    fun getAllStaff(): Flow<List<DbStaff>>
    suspend fun getStaffById(staffId: String): DbStaff?
    suspend fun getStaffByCredentials(username: String, password: String): DbStaff?
    suspend fun addStaff(staff: DbStaff)
    suspend fun removeStaff(staffId: String)
}
