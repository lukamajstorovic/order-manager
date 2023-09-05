package agency.five.codebase.android.ordermanager.ui.menu

import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateMenuItemRoute(
    snackbarHostState: SnackbarHostState,
    onClickCreateButton: (name: String) -> Unit,
) {
    CreateMenuItemScreen(
        snackbarHostState = snackbarHostState,
        onClickCreateButton = onClickCreateButton,
    )
}

@Composable
private fun CreateMenuItemScreen(
    snackbarHostState: SnackbarHostState,
    onClickCreateButton: (name: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var name by remember { mutableStateOf(TextFieldValue(text = "")) }
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .background(color = LightGray)
                .padding(horizontal = 16.dp, vertical = 20.dp)

        ) {
            Text(
                text = "Menu item creation",
                color = DarkGreen,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .align(Alignment.CenterHorizontally)
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
            Button(
                shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
                onClick = {
                    onClickCreateButton(
                        name.text,
                    )
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = LightGray,
                    backgroundColor = LightGray
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 80.dp)
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "Create",
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
        BottomSnackbar(
            snackbarHostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
