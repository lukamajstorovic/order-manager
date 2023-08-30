package agency.five.codebase.android.ordermanager.ui.order.di

import agency.five.codebase.android.ordermanager.ui.order.OrdersViewModel
import agency.five.codebase.android.ordermanager.ui.order.mapper.OrdersMapper
import agency.five.codebase.android.ordermanager.ui.order.mapper.OrdersMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ordersModule = module {
    viewModel {
        OrdersViewModel(
            get(),
            get()
        )
    }
    single<OrdersMapper> { OrdersMapperImpl() }
}
