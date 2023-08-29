package agency.five.codebase.android.ordermanager.ui.confirmorder

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_3
import agency.five.codebase.android.ordermanager.ui.component.service.OrderItem
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmOrderRoute(
    viewModel: ConfirmOrderViewModel,
    onClickConfirmOrder: (String) -> Unit
) {
    val confirmOrderViewState by viewModel.confirmOrderViewState.collectAsState()

    ConfirmOrderScreen(
        confirmOrderViewState = confirmOrderViewState,
        onClickConfirmOrder = onClickConfirmOrder,
        onClickRemoveItem = { id -> viewModel.subtractOrderItemAmount(id) }
    )
}


@Composable
private fun ConfirmOrderScreen(
    modifier: Modifier = Modifier,
    confirmOrderViewState: ConfirmOrderViewState,
    onClickConfirmOrder: (String) -> Unit,
    onClickRemoveItem: (Int) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf(TextFieldValue(text = "")) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = LightGray)
    ) {
        Row(
            modifier = Modifier
                .weight(WEIGHT_1)
        ) {
            Text(
                text = stringResource(id = R.string.table_number),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .weight(WEIGHT_3)
                    .padding(20.dp)
                    .align(CenterVertically)
            )
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .align(CenterVertically),
                shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                elevation = 10.dp,
                backgroundColor = LightGray,
            ) {
                TextField(
                    value = text,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        text = it
                    },
                    modifier = modifier
                        .width(100.dp)
                        .wrapContentSize(Alignment.Center)
                        .align(CenterVertically),
                    shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = LightGray,
                        cursorColor = DarkGreen,
                        disabledLabelColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 40.sp,
                        color = DarkGreen,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Center
                    ),
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    )
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(WEIGHT_3)
        ) {
            items(
                confirmOrderViewState
                    .confirmOrderItemViewStateCollection
                    .size
            ) { index ->
                OrderItem(
                    confirmOrderItemViewState = confirmOrderViewState
                        .confirmOrderItemViewStateCollection[index],
                    modifier = Modifier.padding(10.dp),
                    onClick = {
                        onClickRemoveItem(
                            confirmOrderViewState
                                .confirmOrderItemViewStateCollection[index].id
                        )
                    },
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(WEIGHT_1)
        ) {
//            Divider(
//                thickness = 1.dp,
//                color = Color.Black,
//                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
//            )
            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                Button(
                    shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                    onClick = {
                        onClickConfirmOrder(text.text)
                    },
                    //border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = LightGray,
                        backgroundColor = LightGray
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Confirm order",
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
            /*OrderButton(
                modifier = Modifier,
                onClick = { onClickCompleteOrder },
                text = stringResource(id = R.string.confirm_order)
            )*/
        }
    }
}

@Preview
@Composable
private fun ConfirmOrderScreenPreview() {
    val confirmOrderItemViewStateCollection = listOf(
        ConfirmOrderItemViewState(
            id = 1,
            name = "whatever 1",
            amount = 3
        ),
        ConfirmOrderItemViewState(
            id = 2,
            name = "whatever 2",
            amount = 2
        ),
        ConfirmOrderItemViewState(
            id = 3,
            name = "whatever 3",
            amount = 1
        ),
        ConfirmOrderItemViewState(
            id = 4,
            name = "whatever 4",
            amount = 4
        ),
    )

    val confirmOrderViewState = ConfirmOrderViewState(
        confirmOrderItemViewStateCollection = confirmOrderItemViewStateCollection
    )
    ConfirmOrderScreen(
        confirmOrderViewState = confirmOrderViewState,
        onClickConfirmOrder = { },
        onClickRemoveItem = { }
    )
}
