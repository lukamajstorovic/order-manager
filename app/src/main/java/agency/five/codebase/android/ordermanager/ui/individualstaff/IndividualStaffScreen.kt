package agency.five.codebase.android.ordermanager.ui.individualstaff

import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.WEIGHT_1
import agency.five.codebase.android.ordermanager.WEIGHT_2
import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.component.NoButton
import agency.five.codebase.android.ordermanager.ui.component.RoleDropdown
import agency.five.codebase.android.ordermanager.ui.component.YesButton
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IndividualStaffRoute(
    viewModel: IndividualStaffViewModel,
    snackbarHostState: SnackbarHostState,
) {
    IndividualStaffScreen(
        modifier = Modifier.fillMaxSize(),
        viewModel = viewModel,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun IndividualStaffScreen(
    modifier: Modifier = Modifier,
    viewModel: IndividualStaffViewModel,
    snackbarHostState: SnackbarHostState,

    ) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val staffData by viewModel.staffData.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .background(color = LightGray)
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .verticalScroll(rememberScrollState())

        ) {
            staffData.let { staff ->
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
                        value = staff.name,
                        onValueChange = {
                            (viewModel::updateStaffState)(staff.copy(name = it))
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = LightGray,
                            cursorColor = DarkGreen,
                            disabledLabelColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = DarkGreen,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
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
                        value = staff.username,
                        onValueChange = {
                            (viewModel::updateStaffState)(staff.copy(username = it))
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = LightGray,
                            cursorColor = DarkGreen,
                            disabledLabelColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = DarkGreen,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
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
                        value = staff.password,
                        onValueChange = {
                            (viewModel::updateStaffState)(staff.copy(password = it))
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = LightGray,
                            cursorColor = DarkGreen,
                            disabledLabelColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = DarkGreen,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
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
                Row {
                    Column(
                        modifier = Modifier.weight(WEIGHT_2)
                    ) {
                        Text(
                            text = "Role",
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
                                .padding(bottom = 20.dp),
                            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                            elevation = 10.dp,
                            backgroundColor = LightGray,
                        ) {
                            RoleDropdown(
                                modifier = Modifier,
                                onItemClick = { role ->
                                    (viewModel::updateStaffState)(staff.copy(role = role))
                                },
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                        keyboardController?.hide()
                                    }
                                ),
                                role = staff.role,
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.weight(WEIGHT_1)
                    ) {
                        Text(
                            text = "Approved",
                            color = DarkGreen,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.Start)
                        )
                        if (staff.approved) {
                            YesButton(
                                modifier = Modifier
                                    .height(IntrinsicSize.Max)
                                    .padding(horizontal = 30.dp),
                                onClick = {
                                    (viewModel::updateStaffState)(staff.copy(approved = false))
                                }
                            )
                        } else {
                            NoButton(
                                modifier = Modifier
                                    .height(IntrinsicSize.Max)
                                    .padding(horizontal = 30.dp),
                                onClick = {
                                    (viewModel::updateStaffState)(staff.copy(approved = true))
                                }
                            )
                        }
                    }
                }
                Button(
                    shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                    onClick = { (viewModel::updateStaff)() },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = LightGray,
                        backgroundColor = LightGray
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 80.dp)
                ) {
                    Text(
                        text = "Save",
                        color = DarkGreen,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }
        BottomSnackbar(
            snackbarHostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
