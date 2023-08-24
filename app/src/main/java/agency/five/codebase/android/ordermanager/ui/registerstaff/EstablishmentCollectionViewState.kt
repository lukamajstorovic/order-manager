package agency.five.codebase.android.ordermanager.ui.registerstaff

data class EstablishmentViewState(
    val id: String,
    val name: String,
)

data class EstablishmentCollectionViewState(
    val establishmentViewStateCollection: List<EstablishmentViewState>
)

