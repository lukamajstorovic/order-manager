package agency.five.codebase.android.ordermanager.ui.component.kitchen

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_4
import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrderItemViewState
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActiveOrder(
    activeOrderItemViewState: ActiveOrderItemViewState,
    modifier: Modifier = Modifier,
    onClickOrder: () -> Unit
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
                .clickable { onClickOrder() }
        ) {
            Text(
                text = stringResource(id = R.string.order_for_table_) + activeOrderItemViewState.tableNumber,
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
                    .align(CenterVertically)
                    .size(30.dp),
                tint = DarkGreen,
            )
        }
    }
}

@Preview
@Composable
private fun ActiveOrderPreview() {
    ActiveOrder(
        activeOrderItemViewState = ActiveOrderItemViewState(
            id = 1,
            tableNumber = "13",
            //itemIdCollection = listOf(1, 2)
        ),
        modifier = Modifier.padding(10.dp),
        onClickOrder = {  }
    )
}
