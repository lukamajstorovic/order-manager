package agency.five.codebase.android.ordermanager.data.service.establishment

import agency.five.codebase.android.ordermanager.data.firebase.repository.establishment.EstablishmentRepository
import agency.five.codebase.android.ordermanager.data.firebase.model.DbEstablishment
import agency.five.codebase.android.ordermanager.model.Establishment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class EstablishmentServiceImpl(
    private val bgDispatcher: CoroutineDispatcher,
    private val establishmentRepository: EstablishmentRepository,
) : EstablishmentService {
    override fun allEstablishments(): Flow<List<Establishment>> =
        establishmentRepository.getAllEstablishments().map {
            it.map { dbEstablishment ->
                dbEstablishment.toEstablishment()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override suspend fun establishmentById(establishmentId: String): Establishment? {
        return withContext(bgDispatcher) {
            val dbEstablishment = establishmentRepository.getEstablishmentById(establishmentId)
            dbEstablishment?.toEstablishment()
        }
    }

    override suspend fun addEstablishment(establishment: Establishment) {
        withContext(bgDispatcher) {
            establishmentRepository.addEstablishment(
                DbEstablishment(
                    name = establishment.name,
                )
            )
        }
    }
}
