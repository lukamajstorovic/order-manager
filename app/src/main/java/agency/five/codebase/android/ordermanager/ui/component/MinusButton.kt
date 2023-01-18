package agency.five.codebase.android.ordermanager.ui.component

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ui.theme.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MinusButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
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
        )
    ) {
        Icon(
            painter = painterResource(R.drawable.minus),
            contentDescription = stringResource(id = R.string.minus)
        )
    }
}

@Preview
@Composable
private fun MinusButtonPreview() {
    MinusButton(
        modifier = Modifier,
        onClick = { }
    )
}
