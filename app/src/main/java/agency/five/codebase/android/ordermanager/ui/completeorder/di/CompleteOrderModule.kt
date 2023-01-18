package agency.five.codebase.android.ordermanager.ui.completeorder.di

import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewModel
import agency.five.codebase.android.ordermanager.ui.completeorder.mapper.CompleteOrderMapper
import agency.five.codebase.android.ordermanager.ui.completeorder.mapper.CompleteOrderMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val completeOrderModule = module {
    viewModel { (orderId: Int) ->
        CompleteOrderViewModel(
            get(),
            get(),
            orderId = orderId
        )
    }
    single<CompleteOrderMapper> { CompleteOrderMapperImpl() }
}
