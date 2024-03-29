package agency.five.codebase.android.ordermanager.ui.component.service

import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_3
import agency.five.codebase.android.ordermanager.ui.component.MinusButton
import agency.five.codebase.android.ordermanager.ui.confirmorder.ConfirmOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
data class OrderItemViewState(
    val itemName: String,
    val itemCount: Int
)
 */

@Composable
fun OrderItem(
    confirmOrderItemViewState: ConfirmOrderItemViewState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = modifier
                .weight(WEIGHT_3),
            shape = CircleShape,
            elevation = 10.dp,
            backgroundColor = LightGray,
        ) {
            Text(
                text = confirmOrderItemViewState.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
        }
        Card(
            modifier = Modifier
                .weight(WEIGHT_1)
                .padding(10.dp),
            shape = CircleShape,
            elevation = 10.dp,
            backgroundColor = LightGray,
        ) {
            Text(
                text = confirmOrderItemViewState.amount.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Card(
            modifier = Modifier
                .weight(WEIGHT_1)
                .padding(10.dp),
            shape = CircleShape,
            elevation = 10.dp,
        ) {
            MinusButton(
                modifier = Modifier
                    .height(IntrinsicSize.Max),
                onClick = { onClick() }
            )
        }
    }
}

@Preview
@Composable
private fun OrderItemPreview() {
    OrderItem(
        confirmOrderItemViewState = ConfirmOrderItemViewState(
            id = 1,
            name = "Coca cola 0.5",
            amount = 2,
        ),
        modifier = Modifier.padding(10.dp),
        onClick = { }
    )
}

