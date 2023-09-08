package agency.five.codebase.android.ordermanager.data.service.establishment

import agency.five.codebase.android.ordermanager.model.Establishment
import kotlinx.coroutines.flow.Flow

interface EstablishmentService {
    fun allEstablishments(): Flow<List<Establishment>>
    suspend fun establishmentById(staffId: String): Establishment?
    suspend fun addEstablishment(establishment: Establishment)
}
