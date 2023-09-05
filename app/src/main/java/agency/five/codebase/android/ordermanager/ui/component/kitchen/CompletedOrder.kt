package agency.five.codebase.android.completedOrdermanager.ui.component.kitchen

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_4
import agency.five.codebase.android.ordermanager.ui.order.CompleteOrderViewStateItem
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CompletedOrder(
    completedOrderItemViewState: CompleteOrderViewStateItem,
    modifier: Modifier = Modifier,
    onClickCompletedOrder: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = CircleShape,
        elevation = 10.dp,
        backgroundColor = LightGray,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClickCompletedOrder() }
        ) {
            Text(
                text = "Table " + completedOrderItemViewState.tableNumber
                        + "\n" + SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(
                    completedOrderItemViewState.createdAt.toDate()
                ),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(WEIGHT_4),
            )
            Icon(
                painter = painterResource(R.drawable.arrow_right),
                contentDescription = stringResource(id = R.string.arrow_right),
                modifier = Modifier
                    .weight(WEIGHT_1)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .size(30.dp),
                tint = DarkGreen,
            )
        }
    }
}
