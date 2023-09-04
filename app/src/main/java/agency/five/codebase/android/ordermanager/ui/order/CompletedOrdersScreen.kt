package agency.five.codebase.android.completedOrdermanager.ui.completedOrder

import agency.five.codebase.android.completedOrdermanager.ui.component.kitchen.CompletedOrder
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrdersViewState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp

@Composable
fun CompletedOrdersRoute(
    viewModel: OrdersViewModel,
    openCompletedOrder: (String) -> Unit
) {
    val completedOrdersViewState: CompletedOrdersViewState by viewModel.completedOrdersViewState.collectAsState()

    CompletedOrdersScreen(
        completedOrdersViewState = completedOrdersViewState,
        onClickCompletedOrder = openCompletedOrder
    )
}

@Composable
private fun CompletedOrdersScreen(
    modifier: Modifier = Modifier,
    completedOrdersViewState: CompletedOrdersViewState,
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
                    .completedOrderItemViewStateCollection
                    .size
            ) { index ->
                CompletedOrder(
                    completedOrderItemViewState = completedOrdersViewState
                        .completedOrderItemViewStateCollection[index],
                    onClickCompletedOrder = {
                        onClickCompletedOrder(
                            completedOrdersViewState
                                .completedOrderItemViewStateCollection[index]
                                .id
                        )
                    },
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
