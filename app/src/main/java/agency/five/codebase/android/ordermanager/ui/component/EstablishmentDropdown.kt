package agency.five.codebase.android.ordermanager.ui.component

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

data class DropdownItem(
    val id: String,
    val name: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstablishmentDropdown(
    dropdownItems: List<DropdownItem>,
    modifier: Modifier,
    onItemClick: (String) -> Unit,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var establishmentName by remember {
        mutableStateOf("")
    }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        TextField(
            value = establishmentName,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        
        ExposedDropdownMenu(expanded = isExpanded, 
            onDismissRequest = { isExpanded = false }
        ) {
            dropdownItems.forEach {item ->
                DropdownMenuItem(text = {
                    Text(text = item.name)
                }, onClick = {
                    onItemClick(item.id)
                    isExpanded = false
                })
            }
        }
    }


}

