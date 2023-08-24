package agency.five.codebase.android.ordermanager.ui.registerstaff.mapper

import agency.five.codebase.android.ordermanager.model.Establishment
import agency.five.codebase.android.ordermanager.ui.registerstaff.EstablishmentCollectionViewState

interface EstablishmentMapper {
    fun toEstablishmentCollectionViewState(establishments: List<Establishment>): EstablishmentCollectionViewState
}
