package id.haaweejee.rawgandroid.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.haaweejee.rawgandroid.ui.common.UiState
import id.haaweejee.rawgandroid.ui.component.AppTopBar
import id.haaweejee.rawgandroid.ui.component.GamesListCard
import id.haaweejee.rawgandroid.ui.viewmodel.FavoriteViewModel
import timber.log.Timber

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    val viewModel = hiltViewModel<FavoriteViewModel>()
    val favorite by viewModel.favoriteGamesState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getFavoriteGamesList()
    }
    Column {
        AppTopBar(title = "Favorite Games")
        Spacer(modifier = modifier.height(16.dp))
        Column(modifier = modifier.padding(horizontal = 16.dp)) {
            favorite.let {
                when (it) {
                    is UiState.Loading -> Timber.d("Load From DB")
                    is UiState.Success -> LazyColumn {
                        items(it.data) { data ->
                            GamesListCard(
                                data = data,
                                clickCard = { id -> navigateToDetail(id) }
                            )
                        }
                    }
                    is UiState.Error -> Text(text = it.errorMessage)
                }
            }
        }
    }
}
