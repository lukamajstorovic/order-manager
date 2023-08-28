package agency.five.codebase.android.ordermanager.ui.individualstaff.di

import agency.five.codebase.android.ordermanager.ui.individualstaff.IndividualStaffViewModel
import androidx.compose.material.SnackbarHostState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val individualStaffModule = module {
    viewModel { (
                    snackbarHostState: SnackbarHostState,
                    staffId: String
                ) ->
        IndividualStaffViewModel(
            get(),
            snackbarHostState = snackbarHostState,
            staffId = staffId,
        )
    }
}
