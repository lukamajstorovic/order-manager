package agency.five.codebase.android.ordermanager.ui.component

import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.enums.StaffRoles
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleDropdown(
    modifier: Modifier = Modifier,
    onItemClick: (StaffRoles) -> Unit,
    keyboardActions: KeyboardActions,
    role: StaffRoles,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = modifier,
    ) {
        TextField(
            value = role.name,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
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
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
            ),
            modifier = Modifier
                .menuAnchor(),
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
                    .padding(0.dp)
                    .background(LightGray),
            ) {
                StaffRoles
                    .entries
                    .forEach { role ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = role.name,
                                    color = DarkGreen,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily.Default,
                                )
                            },
                            onClick = {
                                onItemClick(role)
                                isExpanded = false
                            },
                        )
                    }
            }
        }

    }
}

@Preview
@Composable
fun RoleDropdown() {
    RoleDropdown(
        onItemClick = {},
        keyboardActions = KeyboardActions.Default,
        role = StaffRoles.NONE,
    )
}
