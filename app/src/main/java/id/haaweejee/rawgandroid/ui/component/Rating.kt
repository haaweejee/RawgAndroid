package id.haaweejee.rawgandroid.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.rawgandroid.ui.theme.SilverChalice

@Composable
fun Rating(
    voteAverage: Double,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                end = 12.dp,
            ),
    ) {
        Icon(
            Icons.Default.Star,
            contentDescription = "favorite",
            tint = Color.Yellow,
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = voteAverage.toString(),
            fontWeight = FontWeight.SemiBold,
            color = SilverChalice,
            fontSize = 14.sp,
        )
    }
}
