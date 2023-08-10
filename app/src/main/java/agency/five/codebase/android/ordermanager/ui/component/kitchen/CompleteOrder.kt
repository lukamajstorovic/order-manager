package agency.five.codebase.android.ordermanager.ui.component.kitchen

import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            onClick = { onClick() },
            //border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = LightGray, backgroundColor = LightGray),
            modifier = Modifier
                .align(Center)
                .padding(20.dp)
        ) {
            Text(
                text = text,
                color = DarkGreen,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CompleteOrderPreview() {
    val products = ArrayList<String>()
    for (i in 0..20) {
        products.add("Coca cola $i")
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(5f)
        ) {
            items(products.size) { index ->
                ItemToComplete(
                    orderedItemViewState = OrderedItemViewState(
                        itemName = products[index],
                        itemCount = 2,
                    ),
                    modifier = Modifier.padding(10.dp),
                )
            }
            //Spacer(modifier = )
        }
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Divider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            )
            OrderButton(
                modifier = Modifier,
                onClick = { },
                text = "Complete order"
            )
        }
    }
    /*
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
        ) {
            ItemToComplete(
                orderedItemViewState = OrderedItemViewState(
                    itemName = "Coca cola 0.5",
                    itemCount = 2,
                ),
                modifier = Modifier.padding(10.dp),
                onClick = { }
            )
            ItemToComplete(
                orderedItemViewState = OrderedItemViewState(
                    itemName = "Coca cola 0.5",
                    itemCount = 2,
                ),
                modifier = Modifier.padding(10.dp),
                onClick = { }
            )
            ItemToComplete(
                orderedItemViewState = OrderedItemViewState(
                    itemName = "Coca cola 0.5",
                    itemCount = 2,
                ),
                modifier = Modifier.padding(10.dp),
                onClick = { }
            )
            ItemToComplete(
                orderedItemViewState = OrderedItemViewState(
                    itemName = "Coca cola 0.5",
                    itemCount = 2,
                ),
                modifier = Modifier.padding(10.dp),
                onClick = { }
            )
            ItemToComplete(
                orderedItemViewState = OrderedItemViewState(
                    itemName = "Coca cola 0.5",
                    itemCount = 2,
                ),
                modifier = Modifier.padding(10.dp),
                onClick = { }
            )
            ItemToComplete(
                orderedItemViewState = OrderedItemViewState(
                    itemName = "Coca cola 0.5",
                    itemCount = 2,
                ),
                modifier = Modifier.padding(10.dp),
                onClick = { }
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {
            Divider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            )
            CompleteOrder(
                modifier = Modifier,
                onClick = { }
            )
        }
    }

     */
}


