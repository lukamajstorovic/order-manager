package agency.five.codebase.android.ordermanager.ui.confirmorder.di

import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderViewModel
import agency.five.codebase.android.ordermanager.ui.confirmorder.mapper.ConfirmOrderMapper
import agency.five.codebase.android.ordermanager.ui.confirmorder.mapper.ConfirmOrderMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val confirmOrderModule = module {
    viewModel {
        ConfirmOrderViewModel(
            get(),
            get(),
        )
    }
    single<ConfirmOrderMapper> { ConfirmOrderMapperImpl() }
}
