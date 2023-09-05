package agency.five.codebase.android.ordermanager.ui.completeorder

import agency.five.codebase.android.ordermanager.WEIGHT_2
import agency.five.codebase.android.ordermanager.WEIGHT_4
import agency.five.codebase.android.ordermanager.ui.component.kitchen.ItemToComplete
import agency.five.codebase.android.ordermanager.ui.component.kitchen.OrderButton
import agency.five.codebase.android.ordermanager.ui.component.kitchen.OrderItemViewState
import agency.five.codebase.android.ordermanager.ui.order.CompletedOrdersViewState
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

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
        Column(
            modifier = Modifier
                .weight(WEIGHT_2)
        ) {
            Text(
                text = "TABLE: " + completedOrdersViewState.tableNumber,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "DATE: " + SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(
                    completedOrdersViewState.createdAt.toDate()
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "CREATED: ${completedOrdersViewState.createOrderStaffId}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "COMPLETED: ${completedOrdersViewState.completeOrderStaffId}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(WEIGHT_4)
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
                .weight(WEIGHT_2)
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
