package agency.five.codebase.android.ordermanager.ui.registerstaff.di

import agency.five.codebase.android.ordermanager.ui.registerstaff.RegisterStaffViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerStaffModule = module {
    viewModel {
        RegisterStaffViewModel(
            get()
        )
    }
}
