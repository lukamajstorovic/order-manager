package agency.five.codebase.android.ordermanager.data.repository.staff

import agency.five.codebase.android.ordermanager.data.firebase.StaffService
import agency.five.codebase.android.ordermanager.model.Staff
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class StaffRepositoryImpl(
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

    override suspend fun staffById(staffId: String): Staff? {
        return withContext(bgDispatcher) {
            val dbStaff = staffService.getStaffById(staffId)
            dbStaff?.toStaff()
        }
    }

    override suspend fun staffByCredentials(username: String, password: String): Staff? {
        return withContext(bgDispatcher) {
            val dbStaff = staffService.getStaffByCredentials(username, password)
            dbStaff?.toStaff()
        }
    }

    override suspend fun staffByEstablishment(establishmentId: String): Flow<List<Staff>> =
        staffService.getStaffByEstablishment(establishmentId).map {
            it.map { dbStaff ->
                dbStaff.toStaff()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override suspend fun addStaff(staff: Staff) {
        withContext(bgDispatcher) {
            staffService.addStaff(
                agency.five.codebase.android.ordermanager.data.firebase.model.DbStaff(
                    username = staff.username,
                    password = staff.password,
                    name = staff.name,
                    role = staff.role,
                    establishmentId = staff.establishmentId,
                    approved = staff.approved,
                )
            )
        }
    }
    override suspend fun removeStaff(staffId: String) {
        withContext(bgDispatcher) {
            staffService.removeStaff(staffId)
        }
    }
}
