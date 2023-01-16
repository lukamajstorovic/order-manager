package agency.five.codebase.android.ordermanager.ui.component.service

import agency.five.codebase.android.ordermanager.R
import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_13
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



data class SelectionCardViewState(
    val id: Int,
    val name: String,
    val iconId: Int,
)

@Composable
fun SelectionCard(
    selectionCardViewState: SelectionCardViewState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val iconSize: Int
    val fontSize: Int
    val dividerSize: Int
    val paddingSize: Int

    iconSize = 100
    fontSize = 20
    dividerSize = 1
    paddingSize = 5

    Card(
        modifier = modifier
            .padding(top = 10.dp, bottom = 10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT_13)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(ROUNDED_CORNER_PERCENT_13))
                .clickable { onClick() },
        ) {
            Image(
                painter = painterResource(
                    id = selectionCardViewState.iconId
                ),
                contentDescription = selectionCardViewState.name,
                modifier = Modifier
                    .size(iconSize.dp)
                    .padding(paddingSize.dp)
                    .align(CenterHorizontally)
            )
            Divider(
                thickness = dividerSize.dp,
                color = Color.Black,
                modifier = Modifier.padding(start = paddingSize.dp, end = paddingSize.dp)
            )
            Text(
                text = selectionCardViewState.name,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = Color.Black,
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
    /*
    val selectionCardInstance = SelectionCardViewState(
        iconId = R.drawable.drinks,
        nameId = R.string.drinks,
        size = "small"
    )
    SelectionCard(
        selectionCardViewState = selectionCardInstance,
        modifier = Modifier.padding(20.dp).width(150.dp)
    )
     */
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
            iconId = R.drawable.drinks,
            name = stringResource(id = R.string.drinks),
            id = 1
        )
        val selectionCardInstance2 = SelectionCardViewState(
            iconId = R.drawable.food,
            name = stringResource(id = R.string.food),
            id = 2,
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
