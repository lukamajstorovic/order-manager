package agency.five.codebase.android.ordermanager.ui.activeorders

import agency.five.codebase.android.ordermanager.ui.activeorders.mapper.ActiveOrdersMapper
import agency.five.codebase.android.ordermanager.ui.activeorders.mapper.ActiveOrdersMapperImpl
import agency.five.codebase.android.ordermanager.ui.component.kitchen.ActiveOrder
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ActiveOrdersRoute(
    viewModel: ActiveOrdersViewModel,
    openOrder: (Int) -> Unit
) {
    val activeOrdersViewState: ActiveOrdersViewState by viewModel.activeOrdersViewState.collectAsState()

    ActiveOrdersScreen(
        activeOrdersViewState = activeOrdersViewState,
        onClickOrder = openOrder
    )
}

@Composable
private fun ActiveOrdersScreen(
    modifier: Modifier = Modifier,
    activeOrdersViewState: ActiveOrdersViewState,
    onClickOrder: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(
                activeOrdersViewState
                    .activeOrderItemViewStateCollection
                    .size
            ) { index ->
                ActiveOrder(
                    activeOrderItemViewState = activeOrdersViewState
                        .activeOrderItemViewStateCollection[index],
                    onClickOrder = {
                        onClickOrder(
                            activeOrdersViewState
                                .activeOrderItemViewStateCollection[index]
                                .id
                        )
                    },
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ActiveOrderScreenPreview() {
    val activeOrderItemViewStateCollection = listOf(
        ActiveOrderItemViewState(
            id = 1,
            tableNumber = "13",
            //itemIdCollection = listOf(1, 2)
        ),
        ActiveOrderItemViewState(
            id = 2,
            tableNumber = "14",
            //itemIdCollection = listOf(1, 2)
        ),
        ActiveOrderItemViewState(
            id = 3,
            tableNumber = "15",
            //itemIdCollection = listOf(1, 2)
        ),
        ActiveOrderItemViewState(
            id = 4,
            tableNumber = "16",
            //itemIdCollection = listOf(1, 2)
        ),
    )

    val activeOrdersViewState = ActiveOrdersViewState(
        activeOrderItemViewStateCollection = activeOrderItemViewStateCollection
    )
    ActiveOrdersScreen(
        activeOrdersViewState = activeOrdersViewState
    ) {

    }
}
