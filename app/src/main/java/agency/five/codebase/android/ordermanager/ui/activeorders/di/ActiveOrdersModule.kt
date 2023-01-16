package agency.five.codebase.android.ordermanager.ui.activeorders.di

import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrdersViewModel
import agency.five.codebase.android.ordermanager.ui.activeorders.mapper.ActiveOrdersMapper
import agency.five.codebase.android.ordermanager.ui.activeorders.mapper.ActiveOrdersMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val activeOrdersModule = module {
    viewModel {
        ActiveOrdersViewModel(
            get(),
            get()
        )
    }
    single<ActiveOrdersMapper> { ActiveOrdersMapperImpl() }
}
