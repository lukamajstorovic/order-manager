package agency.five.codebase.android.ordermanager.ui.staff

import agency.five.codebase.android.ordermanager.GRID_COUNT
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.ui.component.staff.StaffCard
import agency.five.codebase.android.ordermanager.ui.component.staff.StaffCardViewState
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StaffRoute(
    viewModel: StaffViewModel,
) {
    val staffViewState by viewModel.staffViewState.collectAsState()

    StaffScreen(
        staffViewState = staffViewState,
    )
}

@Composable
private fun StaffScreen(
    staffViewState: StaffViewState,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_COUNT),
        modifier = Modifier
            .background(LightGray)
            .fillMaxSize()
            .padding(start = 5.dp, end = 5.dp, top = 15.dp)
    ) {
        items(
            items = staffViewState.staffCardViewStateCollection,
            key = { staffCardViewState: StaffCardViewState ->
                staffCardViewState.id
            }
        ) { currentStaffCard: StaffCardViewState ->
            StaffCard(
                staffCardViewState = currentStaffCard,
                modifier = Modifier,
                //.align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Preview
@Composable
private fun StaffScreenPreview() {

    val staffViewState = StaffViewState(
        listOf(
            StaffCardViewState(
                id = 1,
                name = "Luka Majstorovic",
                role = StaffRoles.ADMIN,
            ),
            StaffCardViewState(
                id = 2,
                name = "Konobar 1",
                role = StaffRoles.WAITER,
            )
        )
    )

    StaffScreen(
        staffViewState = staffViewState,
    )
}
