package agency.five.codebase.android.ordermanager.ui.component

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_13
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuItemDeleteCard(
    menuItem: MenuItem,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    val fontSize: Int = 20
    val paddingSize: Int = 5

    Card(
        modifier = modifier
            .padding(10.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_13),
        backgroundColor = LightGray
    ) {

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(ROUNDED_CORNER_PERCENT_13))
        ) {
            Text(
                text = menuItem.name,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(paddingSize.dp)
                    .align(Alignment.CenterHorizontally)
            )
            IconButton(
                onClick = { onClick(menuItem.id) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete menu item",
                )
            }
        }
    }
}

@Preview
@Composable
private fun MenuItemDeleteCardPreview() {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color.LightGray,
                        Color.White
                    )
                )
            )
            .fillMaxSize()
    ) {
        val menuItemInstance1 = MenuItem(
            name = stringResource(id = R.string.drinks),
            id = "1",
            establishmentId = "",
        )
        val menuItemInstance2 = MenuItem(
            name = stringResource(id = R.string.food),
            id = "2",
            establishmentId = "",
        )
        MenuItemDeleteCard(
            menuItem = menuItemInstance1,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            onClick = { }
        )
        MenuItemDeleteCard(
            menuItem = menuItemInstance2,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            onClick = { }
        )
    }
}
