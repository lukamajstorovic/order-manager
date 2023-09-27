package agency.five.codebase.android.ordermanager.data.firebase.repository.establishment

import agency.five.codebase.android.ordermanager.data.firebase.model.DbEstablishment
import kotlinx.coroutines.flow.Flow

interface EstablishmentRepository {
    fun getAllEstablishments(): Flow<List<DbEstablishment>>
    suspend fun getEstablishmentById(staffId: String): DbEstablishment?
    suspend fun addEstablishment(establishment: DbEstablishment)
}
