package agency.five.codebase.android.ordermanager.ui.menu

import agency.five.codebase.android.ordermanager.GRID_COUNT
import agency.five.codebase.android.ordermanager.model.MenuItem
import agency.five.codebase.android.ordermanager.ui.component.BottomSnackbar
import agency.five.codebase.android.ordermanager.ui.component.MenuItemDeleteCard
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeleteMenuItemRoute(
    snackbarHostState: SnackbarHostState,
    onClickDeleteButton: (id: String) -> Unit,
    viewModel: MenuViewModel,
) {
    val menuItemCollection by viewModel.menuItems.collectAsState()
    DeleteMenuItemScreen(
        snackbarHostState = snackbarHostState,
        onClickDeleteButton = onClickDeleteButton,
        menuItemCollection = menuItemCollection,
    )
}

@Composable
private fun DeleteMenuItemScreen(
    snackbarHostState: SnackbarHostState,
    onClickDeleteButton: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    menuItemCollection: List<MenuItem>,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .background(color = LightGray)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Menu item removal",
                color = DarkGreen,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(GRID_COUNT),
                modifier = Modifier
                    .background(LightGray)
                    .fillMaxSize()
                    .padding(start = 5.dp, end = 5.dp, top = 15.dp)
            ) {
                items(
                    items = menuItemCollection,
                    key = { menuItem: MenuItem ->
                        menuItem.id
                    }
                ) { currentMenuItem: MenuItem ->
                    MenuItemDeleteCard(
                        menuItem = currentMenuItem,
                        modifier = Modifier,
                        onClick = onClickDeleteButton,
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
