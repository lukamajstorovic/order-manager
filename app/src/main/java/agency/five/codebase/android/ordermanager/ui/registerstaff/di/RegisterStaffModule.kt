package agency.five.codebase.android.ordermanager.ui.registerstaff.di

import agency.five.codebase.android.ordermanager.ui.registerstaff.RegisterStaffViewModel
import agency.five.codebase.android.ordermanager.ui.registerstaff.mapper.EstablishmentMapper
import agency.five.codebase.android.ordermanager.ui.registerstaff.mapper.EstablishmentMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerStaffModule = module {
    viewModel {
        RegisterStaffViewModel(
            get(),
            get(),
            get(),
        )
    }
    single<EstablishmentMapper> { EstablishmentMapperImpl() }
}
