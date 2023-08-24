package agency.five.codebase.android.ordermanager.data.repository.establishment

import agency.five.codebase.android.ordermanager.model.Establishment
import agency.five.codebase.android.ordermanager.model.Staff
import kotlinx.coroutines.flow.Flow

interface EstablishmentRepository {
    fun allEstablishments(): Flow<List<Establishment>>
    suspend fun establishmentById(staffId: String): Establishment?
    suspend fun addEstablishment(establishment: Establishment)
}
