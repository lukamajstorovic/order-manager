package agency.five.codebase.android.ordermanager.ui.component.staff

import agency.five.codebase.android.ordermanager.ROUNDED_CORNER_PERCENT_13
import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.ui.theme.DarkGreen
import agency.five.codebase.android.ordermanager.ui.theme.LightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class StaffCardViewState(
    val id: String,
    val name: String,
    val role: StaffRoles,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StaffCard(
    staffCardViewState: StaffCardViewState,
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
        backgroundColor = LightGray,
        onClick = {
            onClick(staffCardViewState.id)
        }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(ROUNDED_CORNER_PERCENT_13))
        ) {
            Text(
                text = staffCardViewState.name,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(paddingSize.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = staffCardViewState.role.toString(),
                fontSize = fontSize.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                color = DarkGreen,
                modifier = Modifier
                    .padding(paddingSize.dp)
                    .align(Alignment.CenterHorizontally)
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
        val staffCardInstance1 = StaffCardViewState(
            id = "1",
            name = "Luka Majstorovic",
            role = StaffRoles.ADMIN,
        )
        val staffCardInstance2 = StaffCardViewState(
            id = "2",
            name = "Konobar 1",
            role = StaffRoles.WAITER,
        )
        StaffCard(
            staffCardViewState = staffCardInstance1,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            onClick = {},
        )
        StaffCard(
            staffCardViewState = staffCardInstance2,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            onClick = {},
        )
    }
}
