package agency.five.codebase.android.ordermanager.ui.component.service

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_13
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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


data class SelectionCardViewState(
    val id: String,
    val name: String,
)

@Composable
fun SelectionCard(
    selectionCardViewState: SelectionCardViewState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    val iconSize: Int = 100
    val fontSize: Int = 20
    val dividerSize: Int = 1
    val paddingSize: Int = 5

    val interactionSource = remember { MutableInteractionSource() }

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
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onClick() }
                ),
        ) {
            Text(
                text = selectionCardViewState.name,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(paddingSize.dp)
                    .align(CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
private fun SelectionCardPreview() {
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
        val selectionCardInstance1 = SelectionCardViewState(
            name = stringResource(id = R.string.drinks),
            id = "1",
        )
        val selectionCardInstance2 = SelectionCardViewState(
            name = stringResource(id = R.string.food),
            id = "2",
        )
        SelectionCard(
            selectionCardViewState = selectionCardInstance1,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            onClick = { }
        )
        SelectionCard(
            selectionCardViewState = selectionCardInstance2,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            onClick = { }
        )
    }
}
