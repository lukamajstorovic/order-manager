package agency.five.codebase.android.ordermanager.ui.staff.di

import agency.five.codebase.android.ordermanager.ui.staff.StaffViewModel
import agency.five.codebase.android.ordermanager.ui.staff.mapper.StaffMapper
import agency.five.codebase.android.ordermanager.ui.staff.mapper.StaffMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val staffModule = module {
    viewModel {
        StaffViewModel(
            get(),
            get(),
        )
    }
    single<StaffMapper> { StaffMapperImpl() }
}
