package agency.five.codebase.android.ordermanager.ui.component

import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.ui.registerstaff.EstablishmentCollectionViewState
import agency.five.codebase.android.ordermanager.ui.registerstaff.EstablishmentViewState
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstablishmentDropdown(
    establishmentCollectionViewState: EstablishmentCollectionViewState,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
    keyboardActions: KeyboardActions,
    focusRequester: FocusRequester,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var establishmentName by remember {
        mutableStateOf("")
    }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = modifier,
    ) {
        TextField(
            value = establishmentName,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                Text(
                    "Pick an establishment",
                    color = DarkGreen,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Default,
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedTextColor = DarkGreen,
                unfocusedTextColor = DarkGreen,
                disabledTextColor = DarkGreen,
                errorTextColor = DarkGreen,
                focusedContainerColor = LightGray,
                unfocusedContainerColor = LightGray,
                disabledContainerColor = LightGray,
                errorContainerColor = LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            textStyle = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .focusRequester(focusRequester),
            keyboardActions = keyboardActions,
        )
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(
                RoundedCornerShape(
                    ROUNDED_CORNER_PERCENT_30
                )
            )
        ) {
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier
                    .exposedDropdownSize()
                    .background(LightGray)
                    .padding(0.dp),
            ) {
                establishmentCollectionViewState.establishmentViewStateCollection.forEach { establishmentViewState ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = establishmentViewState.name,
                                color = DarkGreen,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.Default,
                            )
                        },
                        onClick = {
                            onItemClick(establishmentViewState.id)
                            isExpanded = false
                            establishmentName = establishmentViewState.name
                        },
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun EstablishmentDropdownPreview() {
    val establishmentViewStateCollection = listOf(
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
    EstablishmentDropdown(
        establishmentCollectionViewState = establishmentCollectionViewState,
        onItemClick = {},
        keyboardActions = KeyboardActions.Default,
        focusRequester = FocusRequester()
    )
}

