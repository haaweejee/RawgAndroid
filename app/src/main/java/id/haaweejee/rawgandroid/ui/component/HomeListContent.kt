package id.haaweejee.rawgandroid.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import id.haaweejee.moviedbandroid.ui.component.molecules.PaginationLoading
import id.haaweejee.rawgandroid.domain.entities.GameEntities
import timber.log.Timber

@Composable
fun HomeListContent(
    modifier: Modifier = Modifier,
    content: LazyPagingItems<GameEntities>,
    navigateToDetail: (Int) -> Unit,
    lazyListState: LazyListState = rememberLazyListState()
) {

    when (content.itemCount) {
        0 -> if (content.loadState.refresh == LoadState.Loading) {
            CustomLoading(modifier = modifier.fillMaxSize())
        }
        else -> {
            LazyColumn(
                state = lazyListState,
            ) {
                items(
                    items = content,
                    key = { it.id },
                ) { game ->
                    if (game != null) {
                        GamesListCard(
                            data = game,
                            clickCard = { navigateToDetail(it) },
                        )
                    }
                }

                when (content.loadState.refresh) { // FIRST LOAD
                    is LoadState.Error -> {
                        item {
                            Timber.d("Error")
                        }
                    }
                    is LoadState.Loading -> { // Loading UI
                        item {
                            CustomLoading(modifier = modifier.fillParentMaxSize())
                        }
                    }
                    else -> Timber.d("No Data")
                }

                when (content.loadState.append) { // Pagination
                    is LoadState.Error -> {
                        item {
                            Timber.d("Error")
                        }
                    }
                    is LoadState.Loading -> { // Pagination Loading UI
                        item {
                            PaginationLoading()
                        }
                    }
                    else -> {
                        Timber.d("No Data")
                    }
                }
            }
        }
    }
}