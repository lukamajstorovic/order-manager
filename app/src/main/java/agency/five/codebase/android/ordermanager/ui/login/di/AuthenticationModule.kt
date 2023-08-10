package agency.five.codebase.android.ordermanager.ui.login.di

import agency.five.codebase.android.ordermanager.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authenticationModule = module {
    viewModel {
        LoginViewModel(
            get(),
        )
    }
}
