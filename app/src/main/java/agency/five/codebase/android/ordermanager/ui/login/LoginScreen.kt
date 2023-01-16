package agency.five.codebase.android.ordermanager.ui.login

import agency.five.codebase.android.ordermanager.*
import agency.five.codebase.android.ordermanager.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginRoute(
    onClickLoginButton: (String) -> Unit,
) {
    LoginScreen(
        onClickLoginButton = onClickLoginButton,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    onClickLoginButton: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(start = 5.dp, end = 5.dp, top = 20.dp, bottom = 20.dp)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color.Black,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .weight(WEIGHT_1)
        )
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current
        var text by remember { mutableStateOf(TextFieldValue(text = "")) }
        OutlinedTextField(
            value = text,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                text = it
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = modifier
                .width(100.dp)
                .align(Alignment.CenterHorizontally)
                .wrapContentSize(Alignment.Center)
                .weight(WEIGHT_2),
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
            ),
        )
        Button(
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            onClick = { onClickLoginButton(text.text) },
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
                .weight(WEIGHT_2)
        ) {
            Text(
                text = stringResource(id = R.string.login),
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.weight(WEIGHT_4))
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        onClickLoginButton = { },
    )
}
