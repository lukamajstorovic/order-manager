package agency.five.codebase.android.ordermanager.data.repository.establishment

import agency.five.codebase.android.ordermanager.data.firebase.EstablishmentService
import agency.five.codebase.android.ordermanager.data.firebase.model.DbEstablishment
import agency.five.codebase.android.ordermanager.model.Establishment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class EstablishmentRepositoryImpl(
    private val bgDispatcher: CoroutineDispatcher,
    private val establishmentService: EstablishmentService,
) : EstablishmentRepository {
    override fun allEstablishments(): Flow<List<Establishment>> =
        establishmentService.getAllEstablishments().map {
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
            val dbEstablishment = establishmentService.getEstablishmentById(establishmentId)
            dbEstablishment?.toEstablishment()
        }
    }

    override suspend fun addEstablishment(establishment: Establishment) {
        withContext(bgDispatcher) {
            establishmentService.addEstablishment(
                DbEstablishment(
                    name = establishment.name,
                )
            )
        }
    }
}
