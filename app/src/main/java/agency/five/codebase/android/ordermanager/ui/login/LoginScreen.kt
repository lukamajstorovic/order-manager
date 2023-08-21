package agency.five.codebase.android.ordermanager.ui.login

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_2
import agency.five.codebase.android.ordermanager.WEIGHT_4
import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    onClickNavigateRegisterButton: () -> Unit,
) {
    LoginScreen(
        snackbarHostState = snackbarHostState,
        onClickLoginButton = onClickLoginButton,
        onClickNavigateRegisterButton = onClickNavigateRegisterButton,
    )
}

@Composable
private fun LoginScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onClickLoginButton: (username: String, password: String) -> Unit,
    onClickNavigateRegisterButton: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(color = LightGray)
            .padding(start = 5.dp, end = 5.dp, top = 20.dp, bottom = 20.dp)
            .fillMaxSize()

    ) {
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current
        var username by remember { mutableStateOf(TextFieldValue(text = "lmajstor")) }
        var password by remember { mutableStateOf(TextFieldValue(text = "123456")) }
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
                .align(Alignment.CenterHorizontally)
                .weight(WEIGHT_2)
                .padding(top = 50.dp)
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
                    fontSize = 30.sp,
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
                    fontSize = 30.sp,
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
                .padding(horizontal = 80.dp, vertical = 15.dp)
                .weight(WEIGHT_1)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.login),
                color = DarkGreen,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
        Button(
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            onClick = { onClickNavigateRegisterButton() },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = LightGray,
                backgroundColor = LightGray
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 80.dp, vertical = 15.dp)
                .weight(WEIGHT_1)
                .fillMaxWidth()
        ) {
            Text(
                text = "To register",
                color = DarkGreen,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(5.dp)
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
        snackbarHostState = SnackbarHostState(),
        onClickNavigateRegisterButton = { }
    )
}
