package agency.five.codebase.android.ordermanager.ui.login

import agency.five.codebase.android.ordermanager.*
import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import kotlinx.coroutines.launch

@Composable
fun LoginRoute(
    onClickLoginButton: (username: String, password: String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    LoginScreen(
        snackbarHostState = snackbarHostState,
        onClickLoginButton = onClickLoginButton,
    )
}

@Composable
private fun LoginScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onClickLoginButton: (username: String, password: String) -> Unit,
) {
    Column(
        modifier = modifier
            .background(color = LightGray)
            .padding(start = 5.dp, end = 5.dp, top = 20.dp, bottom = 20.dp)
            .fillMaxSize()

    ) {
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current
        var username by remember { mutableStateOf(TextFieldValue(text = "")) }
        var password by remember { mutableStateOf(TextFieldValue(text = "")) }
        val scope = rememberCoroutineScope()
        val focusRequester = remember { FocusRequester() }
        Text(
            text = stringResource(id = R.string.app_name),
            color = DarkGreen,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top=50.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally)
                .weight(WEIGHT_2)
        )
        Text(
            text = "Username",
            color = DarkGreen,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Start)
        )
        Card(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            elevation = 10.dp,
            backgroundColor = LightGray,
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                },
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize(Alignment.Center)
                    .weight(WEIGHT_4),
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
                        scope.launch { focusRequester.requestFocus() }
                    }
                ),
            )
        }
        Text(
            text = "Password",
            color = DarkGreen,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Start)
        )
        Card(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            elevation = 10.dp,
            backgroundColor = LightGray,
        ) {
            OutlinedTextField(
                value = password,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    password = it
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize(Alignment.Center)
                    .weight(WEIGHT_4)
                    .focusRequester(focusRequester),
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
                ),
            )
        }
        Button(
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            onClick = { onClickLoginButton(username.text, password.text) },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = LightGray,
                backgroundColor = LightGray
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
                .weight(WEIGHT_2)
        ) {
            Text(
                text = stringResource(id = R.string.login),
                color = DarkGreen,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        BottomSnackbar(
            snackbarHostState = snackbarHostState,
            modifier = Modifier.weight(WEIGHT_1)
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    val username = "username"
    val password = "password"
    LoginScreen(
        onClickLoginButton = { username, password -> },
        snackbarHostState = SnackbarHostState()
    )
}
