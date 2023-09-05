package agency.five.codebase.android.ordermanager.ui.completeorder.di

import agency.five.codebase.android.ordermanager.ui.completeorder.CompleteOrderViewModel
import agency.five.codebase.android.ordermanager.ui.completeorder.mapper.CompleteOrderMapper
import agency.five.codebase.android.ordermanager.ui.completeorder.mapper.CompleteOrderMapperImpl
import androidx.compose.material.SnackbarHostState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val completeOrderModule = module {
    viewModel { (orderId: String, snackbarHostState: SnackbarHostState) ->
        CompleteOrderViewModel(
            get(),
            get(),
            orderId = orderId,
            snackbarHostState = snackbarHostState,
        )
    }
    single<CompleteOrderMapper> { CompleteOrderMapperImpl() }
}
