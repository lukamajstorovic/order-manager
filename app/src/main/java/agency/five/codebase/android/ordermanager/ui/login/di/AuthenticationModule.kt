package agency.five.codebase.android.ordermanager.ui.login.di

import agency.five.codebase.android.ordermanager.ui.login.LoginViewModel
import agency.five.codebase.android.ordermanager.ui.staff.StaffViewModel
import agency.five.codebase.android.ordermanager.ui.staff.mapper.StaffMapper
import agency.five.codebase.android.ordermanager.ui.staff.mapper.StaffMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authenticationModule = module {
    viewModel {
        LoginViewModel(
            get(),
        )
    }
}
