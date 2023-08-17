@file:OptIn(ExperimentalMaterial3Api::class)

package id.haaweejee.rawgandroid.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import id.haaweejee.rawgandroid.ui.theme.DarkBlueGrey

@Composable
fun DetailTopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean,
) {
    TopAppBar(
        title = { Text(text = "Detail", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DarkBlueGrey
        ),
        actions = {
            FavoriteButton(isFavorite = isFavorite) {
                onFavoriteClick()
            }
        }
    )
}