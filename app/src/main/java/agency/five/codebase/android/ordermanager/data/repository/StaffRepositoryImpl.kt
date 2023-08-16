package agency.five.codebase.android.ordermanager.data.repository

import agency.five.codebase.android.ordermanager.data.firebase.StaffService
import agency.five.codebase.android.ordermanager.data.room.model.DbStaff
import agency.five.codebase.android.ordermanager.data.room.dao.StaffDao
import agency.five.codebase.android.ordermanager.model.Staff
import com.google.firebase.Timestamp
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
    private val staffService: StaffService,
) : StaffRepository {
    override fun allStaff(): Flow<List<Staff>> =
        staffService.getAllStaff().map {
            it.map { dbStaff ->
                dbStaff.toStaff()
            }
        }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1,
        )

    override fun staffById(staffId: Long): Flow<Staff> =
        staffDao.getStaffById(staffId).map { dbStaff ->
            Staff(
                id = "dbStaff.id",
                username = dbStaff.username,
                password = dbStaff.password,
                name = dbStaff.name,
                role = dbStaff.role,
                createdAt = Timestamp.now()
            )
        }

    override suspend fun staffByCredentials(username: String, password: String): Staff? {
        return withContext(bgDispatcher) {
            val dbStaff = staffDao.getStaffByCredentials(username, password)
            dbStaff?.toModel()
        }
    }


    private fun DbStaff.toModel(): Staff {
        return Staff(
            id = "id",
            username = username,
            password = password,
            name = name,
            role = role,
            createdAt = Timestamp.now()
        )
    }

    override suspend fun addStaff(staff: Staff) {
        withContext(bgDispatcher) {
            staffDao.insertStaff(
                DbStaff(
                    username = staff.username,
                    password = staff.password,
                    name = staff.name,
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
