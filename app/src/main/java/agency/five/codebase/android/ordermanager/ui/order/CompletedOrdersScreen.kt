package agency.five.codebase.android.completedOrdermanager.ui.completedOrder

import agency.five.codebase.android.completedOrdermanager.ui.component.kitchen.CompletedOrder
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrderViewStateItemCollectionViewState
import agency.five.codebase.android.ordermanager.ui.order.OrdersViewModel
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CompletedOrdersRoute(
    viewModel: OrdersViewModel,
    openCompletedOrder: (String) -> Unit
) {
    val completedOrdersViewState: CompletedOrderViewStateItemCollectionViewState by viewModel.completedOrdersViewState.collectAsState()

    CompletedOrdersScreen(
        completedOrdersViewState = completedOrdersViewState,
        onClickCompletedOrder = openCompletedOrder
    )
}

@Composable
private fun CompletedOrdersScreen(
    modifier: Modifier = Modifier,
    completedOrdersViewState: CompletedOrderViewStateItemCollectionViewState,
    onClickCompletedOrder: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightGray)
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(
                completedOrdersViewState
                    .completedOrderViewStateItemCollection
                    .size
            ) { index ->
                CompletedOrder(
                    completedOrderItemViewState =
                    completedOrdersViewState
                        .completedOrderViewStateItemCollection[index],
                    onClickCompletedOrder = {
                        onClickCompletedOrder(
                            completedOrdersViewState
                                .completedOrderViewStateItemCollection[index]
                                .id
                        )
                    },
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
