package agency.five.codebase.android.ordermanager.ui.completeorder

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_5
import agency.five.codebase.android.ordermanager.ui.component.kitchen.ItemToComplete
import agency.five.codebase.android.ordermanager.ui.component.kitchen.OrderButton
import agency.five.codebase.android.ordermanager.ui.component.kitchen.OrderedItemViewState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CompleteOrderRoute(
    viewModel: CompleteOrderViewModel,
    onCompleteOrder: () -> Unit,
) {
    val completeOrderViewState: CompleteOrderViewState by viewModel.completeOrderViewState.collectAsState()
    CompleteOrderScreen(
        modifier = Modifier.fillMaxSize(),
        completeOrderViewState = completeOrderViewState,
        onClickCompleteOrder = { orderId ->
            viewModel.completeOrder(orderId)
            onCompleteOrder()
        },
    )
}

@Composable
private fun CompleteOrderScreen(
    modifier: Modifier = Modifier,
    completeOrderViewState: CompleteOrderViewState,
    onClickCompleteOrder: (Int) -> Unit,
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
                    orderedItemViewState = OrderedItemViewState(
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
        Column(
            modifier = Modifier
                .weight(WEIGHT_1)
        ) {
            OrderButton(
                modifier = Modifier,
                onClick = {
                    onClickCompleteOrder(completeOrderViewState.orderId)
                },
                text = stringResource(id = R.string.complete_order)
            )
        }
    }
}

@Preview
@Composable
private fun CompleteOrderScreenPreview() {
    val completeOrderItemViewStateCollection = listOf(
        CompleteOrderItemViewState(
            id = 1,
            name = "whatever 1",
            amount = 3
        ),
        CompleteOrderItemViewState(
            id = 2,
            name = "whatever 2",
            amount = 2
        ),
        CompleteOrderItemViewState(
            id = 3,
            name = "whatever 3",
            amount = 1
        ),
        CompleteOrderItemViewState(
            id = 4,
            name = "whatever 4",
            amount = 4
        ),
    )

    val completeOrderViewState = CompleteOrderViewState(
        orderId = 1,
        completeOrderItemViewStateCollection = completeOrderItemViewStateCollection
    )
    CompleteOrderScreen(
        completeOrderViewState = completeOrderViewState,
        onClickCompleteOrder = { }
    )
}
