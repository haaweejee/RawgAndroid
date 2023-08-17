package id.haaweejee.rawgandroid.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.ui.theme.SilverChalice

@Composable
fun GamesListCard(
    modifier: Modifier = Modifier,
    data: GameEntities,
    clickCard: (Int) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = modifier
            .padding(bottom = 12.dp),
    ) {
        Row(
            modifier = modifier.clickable {
                clickCard(data.id)
            },
        ) {
            AsyncImage(
                model = data.gamesImage,
                contentDescription = data.gamesImage,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .width(150.dp)
                    .height(125.dp),
            )
            Column {
                Text(
                    text = data.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            start = 12.dp,
                            end = 8.dp,
                            bottom = 4.dp,
                        ),
                )
                Text(
                    text = data.releasedDate,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = SilverChalice,
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 8.dp,
                            bottom = 2.dp,
                        ),
                )
                Rating(voteAverage = data.rating)
            }
        }
    }

}
