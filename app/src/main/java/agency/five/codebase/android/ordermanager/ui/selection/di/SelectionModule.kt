package agency.five.codebase.android.ordermanager.ui.selection.di

import agency.five.codebase.android.ordermanager.ui.selection.SelectionViewModel
import agency.five.codebase.android.ordermanager.ui.selection.mapper.SelectionMapper
import agency.five.codebase.android.ordermanager.ui.selection.mapper.SelectionMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val selectionModule = module {
    viewModel { (establishmentId: String) ->
        SelectionViewModel(
            get(),
            get(),
            get(),
            establishmentId = establishmentId
        )
    }
    single<SelectionMapper> { SelectionMapperImpl() }
}
