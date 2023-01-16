package agency.five.codebase.android.ordermanager.ui.selection

import agency.five.codebase.android.ordermanager.GRID_COUNT
import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.mock.MenuItemMock
import agency.five.codebase.android.ordermanager.ui.component.service.SelectionCard
import agency.five.codebase.android.ordermanager.ui.component.service.SelectionCardViewState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
) {
    val selectionViewState by viewModel.selectionViewState.collectAsState()

    viewModel.addMenuItem(MenuItemMock.getMenuItemList()[0])
    viewModel.addMenuItem(MenuItemMock.getMenuItemList()[1])

    SelectionScreen(
        modifier = Modifier.fillMaxSize(),
        selectionViewState = selectionViewState,
        onClickSelectionCard = { orderedItem -> viewModel.addOrderedItem(orderedItem) }
    )
}

@Composable
private fun SelectionScreen(
    modifier: Modifier = Modifier,
    selectionViewState: SelectionViewState,
    onClickSelectionCard: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(start = 5.dp, end = 5.dp, top = 20.dp, bottom = 20.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(GRID_COUNT),
            modifier = Modifier

        ) {
            items(
                items = selectionViewState.selectionCardViewStateCollection,
                key = { selectionCardViewState: SelectionCardViewState ->
                    selectionCardViewState.id
                }
            ) { currentSelectionCard: SelectionCardViewState ->
                SelectionCard(
                    selectionCardViewState = currentSelectionCard,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp),
                    onClick = {
                        onClickSelectionCard(
                            currentSelectionCard.name,
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun FavoritesScreenPreview() {
    val selectionCardModifier = Modifier
        .fillMaxSize()

    val selectionViewState = SelectionViewState(
        listOf(
            SelectionCardViewState(
                id = 1,
                iconId = R.drawable.drinks,
                name = stringResource(id = R.string.drinks),
            ),
            SelectionCardViewState(
                id = 2,
                iconId = R.drawable.food,
                name = stringResource(id = R.string.food),
            )
        )
    )

    SelectionScreen(
        modifier = selectionCardModifier,
        selectionViewState = selectionViewState,
        onClickSelectionCard = { },
    )
}
