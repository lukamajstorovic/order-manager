package agency.five.codebase.android.ordermanager.ui.order

import agency.five.codebase.android.ordermanager.ui.component.kitchen.Order
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

@Composable
fun OrdersRoute(
    viewModel: OrdersViewModel,
    openOrder: (String) -> Unit
) {
    val ordersViewState: OrdersViewState by viewModel.ordersViewState.collectAsState()

    OrdersScreen(
        ordersViewState = ordersViewState,
        onClickOrder = openOrder
    )
}

@Composable
private fun OrdersScreen(
    modifier: Modifier = Modifier,
    ordersViewState: OrdersViewState,
    onClickOrder: (String) -> Unit,
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
                ordersViewState
                    .orderItemViewStateCollection
                    .size
            ) { index ->
                Order(
                    orderItemViewState = ordersViewState
                        .orderItemViewStateCollection[index],
                    onClickOrder = {
                        onClickOrder(
                            ordersViewState
                                .orderItemViewStateCollection[index]
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
private fun OrderScreenPreview() {
    val orderItemViewStateCollection = listOf(
        OrderItemViewState(
            id = "1",
            tableNumber = "13",
            //itemIdCollection = listOf(1, 2)
        ),
        OrderItemViewState(
            id = "2",
            tableNumber = "14",
            //itemIdCollection = listOf(1, 2)
        ),
        OrderItemViewState(
            id = "3",
            tableNumber = "15",
            //itemIdCollection = listOf(1, 2)
        ),
        OrderItemViewState(
            id = "4",
            tableNumber = "16",
            //itemIdCollection = listOf(1, 2)
        ),
    )

    val ordersViewState = OrdersViewState(
        orderItemViewStateCollection = orderItemViewStateCollection
    )
    OrdersScreen(
        ordersViewState = ordersViewState
    ) {

    }
}
