package agency.five.codebase.android.ordermanager.ui.menu.di

import agency.five.codebase.android.ordermanager.ui.menu.MenuViewModel
import androidx.compose.material.SnackbarHostState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val menuModule = module {
    viewModel { (snackbarHostState: SnackbarHostState, establishmentId: String) ->
        MenuViewModel(
            snackbarHostState = snackbarHostState,
            get(),
            establishmentId = establishmentId,
        )
    }
}
