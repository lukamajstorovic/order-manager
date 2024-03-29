package agency.five.codebase.android.ordermanager.ui.selection

import agency.five.codebase.android.ordermanager.GRID_COUNT
import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.component.service.SelectionCard
import agency.five.codebase.android.ordermanager.ui.component.service.SelectionCardViewState
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SelectionRoute(
    viewModel: SelectionViewModel,
    snackbarHostState: SnackbarHostState,
) {
    val selectionViewState by viewModel.selectionViewState.collectAsState()

    SelectionScreen(
        selectionViewState = selectionViewState,
        onClickSelectionCard = { orderItemName ->
            viewModel.addOrderItemOrIncrementAmount(
                orderItemName
            )
        },
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun SelectionScreen(
    selectionViewState: SelectionViewState,
    onClickSelectionCard: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(GRID_COUNT),
            modifier = Modifier
                .background(LightGray)
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp, top = 15.dp)
        ) {
            items(
                items = selectionViewState.selectionCardViewStateCollection,
                key = { selectionCardViewState: SelectionCardViewState ->
                    selectionCardViewState.id
                }
            ) { currentSelectionCard: SelectionCardViewState ->
                SelectionCard(
                    selectionCardViewState = currentSelectionCard,
                    modifier = Modifier,
                    //.align(Alignment.CenterHorizontally),
                    onClick = {
                        onClickSelectionCard(
                            currentSelectionCard.name,
                        )
                    }
                )
            }
        }
        BottomSnackbar(
            snackbarHostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
private fun SelectionScreenPreview() {

    val selectionViewState = SelectionViewState(
        listOf(
            SelectionCardViewState(
                id = "1",
                name = stringResource(id = R.string.drinks),
            ),
            SelectionCardViewState(
                id = "2",
                name = stringResource(id = R.string.food),
            )
        )
    )
    SelectionScreen(
        selectionViewState = selectionViewState,
        onClickSelectionCard = {},
        snackbarHostState = SnackbarHostState(),
    )
}
