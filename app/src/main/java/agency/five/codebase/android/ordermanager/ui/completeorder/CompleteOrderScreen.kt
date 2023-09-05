package agency.five.codebase.android.ordermanager.ui.completeorder

import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_5
import agency.five.codebase.android.ordermanager.data.currentuser.UserData
import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.component.kitchen.ItemToComplete
import agency.five.codebase.android.ordermanager.ui.component.kitchen.OrderButton
import agency.five.codebase.android.ordermanager.ui.component.kitchen.OrderItemViewState
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CompleteOrderRoute(
    viewModel: CompleteOrderViewModel,
    onClickButton: () -> Unit,
    currentUser: UserData,
    snackbarHostState: SnackbarHostState,
) {
    val completeOrderViewState: CompleteOrderViewState by viewModel.completeOrderViewState.collectAsState()
    CompleteOrderScreen(
        modifier = Modifier.fillMaxSize(),
        completeOrderViewState = completeOrderViewState,
        onClickCompleteOrder = { orderId ->
            viewModel.completeOrder(currentUser, orderId)
            onClickButton()
        },
        onCancelOrder = { orderId ->
            viewModel.cancelOrder(orderId)
            onClickButton()
        },
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun CompleteOrderScreen(
    modifier: Modifier = Modifier,
    completeOrderViewState: CompleteOrderViewState,
    onClickCompleteOrder: (String) -> Unit,
    onCancelOrder: (id: String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = LightGray)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(WEIGHT_5)
            ) {
                items(completeOrderViewState.completeOrderItemViewStateCollection.size) { index ->
                    ItemToComplete(
                        orderItemViewState = OrderItemViewState(
                            itemName = completeOrderViewState
                                .completeOrderItemViewStateCollection[index]
                                .name,
                            itemCount = completeOrderViewState
                                .completeOrderItemViewStateCollection[index]
                                .amount,
                        ),
                        modifier = Modifier.padding(10.dp),
                    )
                }
            }
            Row(
                modifier = Modifier
                    .weight(WEIGHT_1)
                    .align(Alignment.CenterHorizontally)
            ) {
                OrderButton(
                    modifier = Modifier,
                    onClick = {
                        onClickCompleteOrder(completeOrderViewState.orderId)
                    },
                    text = "Complete"
                )
                OrderButton(
                    modifier = Modifier,
                    onClick = {
                        onCancelOrder(completeOrderViewState.orderId)
                    },
                    text = "Cancel"
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
private fun CompleteOrderScreenPreview() {
    val completeOrderItemViewStateCollection = listOf(
        CompleteOrderItemViewState(
            id = "1",
            name = "whatever 1",
            amount = 3
        ),
        CompleteOrderItemViewState(
            id = "2",
            name = "whatever 2",
            amount = 2
        ),
        CompleteOrderItemViewState(
            id = "3",
            name = "whatever 3",
            amount = 1
        ),
        CompleteOrderItemViewState(
            id = "4",
            name = "whatever 4",
            amount = 4
        ),
    )

    val completeOrderViewState = CompleteOrderViewState(
        orderId = "1",
        completeOrderItemViewStateCollection = completeOrderItemViewStateCollection
    )
    CompleteOrderScreen(
        completeOrderViewState = completeOrderViewState,
        onClickCompleteOrder = { },
        onCancelOrder = { },
        snackbarHostState = SnackbarHostState(),
    )
}
