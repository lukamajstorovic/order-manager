package agency.five.codebase.android.ordermanager.ui.order

import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.component.kitchen.Order
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrdersRoute(
    viewModel: OrdersViewModel,
    openOrder: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val ordersViewState: OrdersViewState by viewModel.ordersViewState.collectAsState()

    OrdersScreen(
        ordersViewState = ordersViewState,
        onClickOrder = openOrder,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun OrdersScreen(
    modifier: Modifier = Modifier,
    ordersViewState: OrdersViewState,
    onClickOrder: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
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
        BottomSnackbar(
            snackbarHostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
