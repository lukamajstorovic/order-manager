package agency.five.codebase.android.ordermanager.ui.completeorder

import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_5
import agency.five.codebase.android.ordermanager.data.currentuser.UserData
import agency.five.codebase.android.ordermanager.ui.component.kitchen.ItemToComplete
import agency.five.codebase.android.ordermanager.ui.component.kitchen.OrderButton
import agency.five.codebase.android.ordermanager.ui.component.kitchen.OrderItemViewState
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrdersViewState
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
fun IndividualCompletedOrderRoute(
    viewModel: CompleteOrderViewModel,
    onBack: () -> Unit,
) {
    val completedOrdersViewState: CompletedOrdersViewState by viewModel.individualCompletedOrderViewState.collectAsState()
    IndividualCompletedOrderScreen(
        modifier = Modifier.fillMaxSize(),
        completedOrdersViewState = completedOrdersViewState,
        onBack = onBack,
    )
}

@Composable
private fun IndividualCompletedOrderScreen(
    modifier: Modifier = Modifier,
    completedOrdersViewState: CompletedOrdersViewState,
    onBack: () -> Unit,
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
            items(completedOrdersViewState.completedOrderItemViewStateCollection.size) { index ->
                ItemToComplete(
                    orderItemViewState = OrderItemViewState(
                        itemName = completedOrdersViewState
                            .completedOrderItemViewStateCollection[index]
                            .name,
                        itemCount = completedOrdersViewState
                            .completedOrderItemViewStateCollection[index]
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
                    onBack()
                },
                text = "Go back"
            )
        }
    }
}
