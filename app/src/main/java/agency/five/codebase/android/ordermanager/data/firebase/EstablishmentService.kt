package agency.five.codebase.android.ordermanager.data.firebase

import agency.five.codebase.android.ordermanager.data.firebase.model.DbEstablishment
import agency.five.codebase.android.ordermanager.data.firebase.model.DbStaff
import kotlinx.coroutines.flow.Flow

interface EstablishmentService {
    fun getAllEstablishments(): Flow<List<DbEstablishment>>
    suspend fun getEstablishmentById(staffId: String): DbEstablishment?
    suspend fun addEstablishment(establishment: DbEstablishment)
}
