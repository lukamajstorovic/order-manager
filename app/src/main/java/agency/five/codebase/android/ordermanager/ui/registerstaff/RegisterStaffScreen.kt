package agency.five.codebase.android.ordermanager.ui.registerstaff

import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_4
import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
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
fun RegisterStaffRoute(
    onClickRegisterButton: (name: String, username: String, password: String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    RegisterStaffScreen(
        snackbarHostState = snackbarHostState,
        onClickRegisterButton = onClickRegisterButton,
    )
}

@Composable
private fun RegisterStaffScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onClickRegisterButton: (name: String, username: String, password: String) -> Unit,
) {
    Column(
        modifier = modifier
            .background(color = LightGray)
            .padding(start = 5.dp, end = 5.dp, top = 20.dp, bottom = 20.dp)
            .fillMaxSize()

    ) {
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current
        var name by remember { mutableStateOf(TextFieldValue(text = "")) }
        var username by remember { mutableStateOf(TextFieldValue(text = "")) }
        var password by remember { mutableStateOf(TextFieldValue(text = "")) }
        val scope = rememberCoroutineScope()
        val focusRequesterUsername = remember { FocusRequester() }
        val focusRequesterPassword = remember { FocusRequester() }
        Text(
            text = "Staff Register",
            color = DarkGreen,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top=50.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally)
                .weight(WEIGHT_1)
        )
        Text(
            text = "Name",
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
                value = name,
                onValueChange = {
                    name = it
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
                        scope.launch { focusRequesterUsername.requestFocus() }
                    }
                ),
            )
        }
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
                    .weight(WEIGHT_4)
                    .focusRequester(focusRequesterUsername),
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
                        scope.launch { focusRequesterPassword.requestFocus() }
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
                    .focusRequester(focusRequesterPassword),
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
            onClick = { onClickRegisterButton(name.text, username.text, password.text) },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = LightGray,
                backgroundColor = LightGray
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
                .weight(WEIGHT_1)
        ) {
            Text(
                text = "Register",
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
    val name = "name"
    val username = "username"
    val password = "password"
    RegisterStaffScreen(
        onClickRegisterButton = { name, username, password -> },
        snackbarHostState = SnackbarHostState()
    )
}
