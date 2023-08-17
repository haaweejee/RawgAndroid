package id.haaweejee.rawgandroid.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.ui.common.UiState
import id.haaweejee.rawgandroid.ui.component.CustomLoading
import id.haaweejee.rawgandroid.ui.component.DetailTopBar
import id.haaweejee.rawgandroid.ui.component.Rating
import id.haaweejee.rawgandroid.ui.theme.SilverChalice
import id.haaweejee.rawgandroid.ui.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    gameId: Int,
    navController: NavController
) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val detail by viewModel.detailState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getDetailGame(gameId)
    }

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        detail.let {
            when (it) {
                is UiState.Loading -> CustomLoading(modifier.fillMaxSize())
                is UiState.Success -> Column {
                    DetailTopBar(
                        navController = navController,
                        isFavorite = it.data.isFavorite,
                        onFavoriteClick = {
                            viewModel.insertGameToFavorite(
                                GameEntities(
                                    id = it.data.id,
                                    name = it.data.name,
                                    rating = it.data.rating,
                                    releasedDate = it.data.releasedDate,
                                    gamesImage = it.data.gamesImage,
                                    isFavorite = true
                                )
                            )
                        }
                    )
                    AsyncImage(
                        model = it.data.gamesImage,
                        contentDescription = it.data.gamesImage,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(250.dp),
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Column(
                        modifier = modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = it.data.gamePublisher,
                                color = SilverChalice,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Rating(voteAverage = it.data.rating)
                        }
                        Spacer(modifier = modifier.height(8.dp))
                        Text(
                            text = it.data.name,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = modifier.height(4.dp))
                        Text(
                            text = it.data.releasedDate,
                            color = SilverChalice,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = modifier.height(16.dp))
                        Text(text = it.data.description)
                    }
                }
                is UiState.Error -> Text(text = it.errorMessage)
            }
        }
    }
}
