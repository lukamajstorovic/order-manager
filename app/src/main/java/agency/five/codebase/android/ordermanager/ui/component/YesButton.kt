package agency.five.codebase.android.ordermanager.ui.component

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun YesButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(0.dp, Color.Transparent),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = DarkGreen,
            backgroundColor = LightGray
        ),
        elevation = ButtonDefaults.elevation(10.dp),
    ) {
        Icon(
            painter = painterResource(R.drawable.yes),
            contentDescription = stringResource(id = R.string.yes)
        )
    }
}

@Preview
@Composable
private fun YesButtonPreview() {
    YesButton(
        modifier = Modifier,
        onClick = { }
    )
}

