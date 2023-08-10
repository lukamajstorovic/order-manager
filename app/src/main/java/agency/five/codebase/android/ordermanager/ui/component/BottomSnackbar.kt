package agency.five.codebase.android.ordermanager.ui.component

import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_30
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BottomSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier,
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier,
    ) { snackbarData ->
        Snackbar(
            modifier = Modifier.padding(16.dp),
            backgroundColor = LightGray,
            shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_30),
            elevation = 10.dp,
            contentColor = DarkGreen,
        ) {
            Text(
                text = snackbarData.message,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
            )
        }
    }
}
