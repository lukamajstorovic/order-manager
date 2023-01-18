package agency.five.codebase.android.ordermanager.ui.confirmorder

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_3
import agency.five.codebase.android.ordermanager.ui.component.kitchen.OrderButton
import agency.five.codebase.android.ordermanager.ui.component.service.OrderedItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
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
    onNavigateToSelectionScreen: () -> Unit,
) {
    val confirmOrderViewState by viewModel.confirmOrderViewState.collectAsState()

    ConfirmOrderScreen(
        confirmOrderViewState = confirmOrderViewState,
        onClickCompleteOrder = { tableNumber ->
            viewModel.confirmOrder(tableNumber)
            onNavigateToSelectionScreen()
        },
        onClickRemoveItem = { id -> viewModel.subtractOrderedItemAmount(id) }
    )
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ConfirmOrderScreen(
    modifier: Modifier = Modifier,
    confirmOrderViewState: ConfirmOrderViewState,
    onClickCompleteOrder: (String) -> Unit,
    onClickRemoveItem: (Int) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf(TextFieldValue(text = "")) }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .weight(WEIGHT_1)
        ) {
            Text(
                text = stringResource(id = R.string.table_number),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = Color.Black,
                modifier = Modifier
                    .weight(WEIGHT_3)
                    .padding(20.dp)
                    .align(CenterVertically)
            )
            OutlinedTextField(
                value = text,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    text = it
                },
                modifier = modifier
                    .width(100.dp)
                    .wrapContentSize(Alignment.Center)
                    .align(CenterVertically)
                    .padding(10.dp),
                shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                ),
                textStyle = TextStyle(
                    fontSize = 40.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
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
        LazyColumn(
            modifier = Modifier
                .weight(WEIGHT_3)
        ) {
            items(
                confirmOrderViewState
                    .confirmOrderItemViewStateCollection
                    .size
            ) { index ->
                OrderedItem(
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
            Divider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            )
            OrderButton(
                modifier = Modifier,
                onClick = { onClickCompleteOrder(text.text) },
                text = stringResource(id = R.string.confirm_order)
            )
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
        onClickCompleteOrder = { },
        onClickRemoveItem = { }
    )
}
