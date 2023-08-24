package agency.five.codebase.android.ordermanager.ui.registerstaff.mapper

import agency.five.codebase.android.ordermanager.model.Establishment
import agency.five.codebase.android.ordermanager.ui.registerstaff.EstablishmentCollectionViewState
import agency.five.codebase.android.ordermanager.ui.registerstaff.EstablishmentViewState

class EstablishmentMapperImpl : EstablishmentMapper {
    override fun toEstablishmentCollectionViewState(establishments: List<Establishment>): EstablishmentCollectionViewState {
        val establishmentViewStateCollection = establishments.map { mapEstablishment(it) }
        return EstablishmentCollectionViewState(
            establishmentViewStateCollection = establishmentViewStateCollection
        )
    }

    private fun mapEstablishment(establishment: Establishment): EstablishmentViewState {
        return EstablishmentViewState(
            id = establishment.id,
            name = establishment.name,
        )
    }
}
