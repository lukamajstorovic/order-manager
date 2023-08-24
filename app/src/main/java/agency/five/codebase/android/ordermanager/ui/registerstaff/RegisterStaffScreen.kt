package agency.five.codebase.android.ordermanager.ui.registerstaff

import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_2
import agency.five.codebase.android.ordermanager.WEIGHT_4
import agency.five.codebase.android.ordermanager.model.Establishment
import agency.five.codebase.android.ordermanager.ui.activeorders.ActiveOrdersViewState
import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.component.EstablishmentDropdown
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
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterStaffRoute(
    snackbarHostState: SnackbarHostState,
    registerStaffViewModel: RegisterStaffViewModel,
    onClickRegisterButton: (name: String, username: String, password: String, establishmentId: String) -> Unit,
    onClickNavigateLoginButton: () -> Unit,
) {
    val establishmentCollectionViewState: EstablishmentCollectionViewState by registerStaffViewModel.establishmentCollectionViewState.collectAsState()
    RegisterStaffScreen(
        snackbarHostState = snackbarHostState,
        onClickRegisterButton = onClickRegisterButton,
        onClickNavigateLoginButton = onClickNavigateLoginButton,
        establishmentCollectionViewState = establishmentCollectionViewState,
    )
}

@Composable
private fun RegisterStaffScreen(
    snackbarHostState: SnackbarHostState,
    establishmentCollectionViewState: EstablishmentCollectionViewState,
    modifier: Modifier = Modifier,
    onClickRegisterButton: (name: String, username: String, password: String, establishmentId: String) -> Unit,
    onClickNavigateLoginButton: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var name by remember { mutableStateOf(TextFieldValue(text = "")) }
    var username by remember { mutableStateOf(TextFieldValue(text = "")) }
    var password by remember { mutableStateOf(TextFieldValue(text = "")) }
    val scope = rememberCoroutineScope()
    val focusRequesterUsername = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterEstablishment = remember { FocusRequester() }
    val registerStaffViewModel: RegisterStaffViewModel = viewModel()
    Column(
        modifier = modifier
            .background(color = LightGray)
            .padding(start = 5.dp, end = 5.dp, top = 20.dp, bottom = 20.dp)
            .fillMaxSize()

    ) {
        Text(
            text = "Staff Register",
            color = DarkGreen,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 50.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally)
                .weight(WEIGHT_2)
        )
        Text(
            text = "Name",
            color = DarkGreen,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
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
                    .weight(WEIGHT_1),
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
                    .focusRequester(focusRequesterUsername)
                    .weight(WEIGHT_1),
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
                    .focusRequester(focusRequesterPassword)
                    .weight(WEIGHT_1),
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
                ),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        scope.launch { focusRequesterEstablishment.requestFocus() }
                    }
                ),
            )
        }
        Card(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            elevation = 10.dp,
            backgroundColor = LightGray,
        ) {
            EstablishmentDropdown(
                establishmentCollectionViewState = establishmentCollectionViewState,
                modifier = Modifier
                    .weight(WEIGHT_1),
                onItemClick = {establishmentId ->
                    (registerStaffViewModel::setName)(establishmentId)
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ),
                focusRequester = focusRequesterEstablishment,
            )
        }
        Button(
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            onClick = { onClickRegisterButton(name.text, username.text, password.text, registerStaffViewModel.establishmentName.value) },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = LightGray,
                backgroundColor = LightGray
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 80.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Register",
                color = DarkGreen,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(WEIGHT_2)
            )
        }
        Button(
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            onClick = { onClickNavigateLoginButton() },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = LightGray,
                backgroundColor = LightGray
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 80.dp, vertical = 20.dp),
        ) {
            Text(
                text = "To login",
                color = DarkGreen,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(WEIGHT_2)
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
private fun RegisterStaffScreenPreview() {
    val name = "name"
    val username = "username"
    val password = "password"
    val establishmentId = "establishmentId"
    val establishmentViewStateCollection = listOf<EstablishmentViewState>(
        EstablishmentViewState(
            id = "1",
            name = "name1"
        ),
        EstablishmentViewState(
            id = "2",
            name = "name2"
        ),
    )
    val establishmentCollectionViewState = EstablishmentCollectionViewState(
        establishmentViewStateCollection = establishmentViewStateCollection,
    )
    RegisterStaffScreen(
        snackbarHostState = SnackbarHostState(),
        onClickRegisterButton = { name, username, password, establishmentId -> },
        onClickNavigateLoginButton = {},
        establishmentCollectionViewState = establishmentCollectionViewState,
    )
}
