package agency.five.codebase.android.ordermanager.ui.selection.di

import agency.five.codebase.android.ordermanager.ui.selection.SelectionViewModel
import agency.five.codebase.android.ordermanager.ui.selection.mapper.SelectionMapper
import agency.five.codebase.android.ordermanager.ui.selection.mapper.SelectionMapperImpl
import androidx.compose.material.SnackbarHostState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val selectionModule = module {
    viewModel { (establishmentId: String, snackbarHostState: SnackbarHostState) ->
        SelectionViewModel(
            get(),
            get(),
            get(),
            establishmentId = establishmentId,
            snackbarHostState = snackbarHostState,
        )
    }
    single<SelectionMapper> { SelectionMapperImpl() }
}
