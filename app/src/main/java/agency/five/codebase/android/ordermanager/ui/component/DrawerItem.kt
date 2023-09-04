package agency.five.codebase.android.ordermanager.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DrawerMenuItem(
    val path: String,
    val text: String
)

@Composable
fun DrawerItem(
    drawerMenuItem: DrawerMenuItem,
    modifier: Modifier = Modifier,
    onItemClick: (DrawerMenuItem) -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            onItemClick(drawerMenuItem)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(8.dp)
        ) {
            Text(
                text = drawerMenuItem.text,
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Divider()
    }
}
