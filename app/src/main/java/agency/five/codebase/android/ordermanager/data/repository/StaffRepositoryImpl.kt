package agency.five.codebase.android.ordermanager.data.repository

import agency.five.codebase.android.ordermanager.data.database.DbStaff
import agency.five.codebase.android.ordermanager.data.database.StaffDao
import agency.five.codebase.android.ordermanager.model.Staff
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class StaffRepositoryImpl(
    private val staffDao: StaffDao,
    private val bgDispatcher: CoroutineDispatcher,
) : StaffRepository {
    override suspend fun allStaff(): Flow<List<Staff>> = withContext(bgDispatcher) {
        staffDao.getAllStaff().map {
            it.map { dbStaff ->
                Staff(
                    id = dbStaff.id,
                    name = dbStaff.name,
                    password = dbStaff.password,
                    role = dbStaff.role,
                )
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )
    }
    override suspend fun staffById(staffId: Long): Flow<Staff> {
        return withContext(bgDispatcher) {
            staffDao.getStaffById(staffId).map { dbStaff ->
                Staff(
                    id = dbStaff.id,
                    name = dbStaff.name,
                    password = dbStaff.password,
                    role = dbStaff.role,
                )
            }
        }
    }
    override suspend fun addStaff(staff: Staff) {
        withContext(bgDispatcher) {
            staffDao.insertStaff(
                DbStaff(
                    id = staff.id,
                    name = staff.name,
                    password = staff.password,
                    role = staff.role,
                )
            )
        }
    }
    override suspend fun removeStaff(staffId: Long) {
        withContext(bgDispatcher) {
            staffDao.deleteStaff(id = staffId)
        }
    }
}
