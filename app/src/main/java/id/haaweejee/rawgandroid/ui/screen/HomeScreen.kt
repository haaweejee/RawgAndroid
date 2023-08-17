package id.haaweejee.rawgandroid.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import id.haaweejee.rawgandroid.ui.component.AppTopBar
import id.haaweejee.rawgandroid.ui.component.HomeListContent
import id.haaweejee.rawgandroid.ui.component.SearchTextField
import id.haaweejee.rawgandroid.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    LaunchedEffect(Unit) {
        viewModel.getGames()
    }

    val games = viewModel.gamesState.collectAsLazyPagingItems()

    var query by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column {
        AppTopBar(title = "Games For You")
        Spacer(modifier = modifier.height(16.dp))
        Column(
            modifier.padding(horizontal = 16.dp)
        ) {
            SearchTextField(
                queryValue = query,
                onValueChange = {
                    query = it
                    viewModel.getGames(it.text)
                }
            )
            Spacer(modifier = modifier.height(12.dp))
            HomeListContent(
                content = games,
                navigateToDetail = { navigateToDetail(it) }
            )
        }
    }
}
