package id.haaweejee.rawgandroid.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit,
) {
    var favorite by remember { mutableStateOf(isFavorite) }

    IconButton(
        onClick = {
            favorite = !favorite
            onFavoriteClick()
        },
        modifier = modifier.size(24.dp),
    ) {
        if (favorite) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "favorite",
                tint = Color.White
            )
        } else {
            Icon(
                Icons.Outlined.FavoriteBorder,
                contentDescription = "favorite",
                tint = Color.White
            )
        }
    }
}
