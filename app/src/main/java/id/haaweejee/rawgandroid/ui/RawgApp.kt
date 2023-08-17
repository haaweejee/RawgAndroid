package id.haaweejee.rawgandroid.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.haaweejee.rawgandroid.ui.component.BottomBar
import id.haaweejee.rawgandroid.ui.navigation.Navigation
import id.haaweejee.rawgandroid.ui.screen.DetailScreen
import id.haaweejee.rawgandroid.ui.screen.FavoriteScreen
import id.haaweejee.rawgandroid.ui.screen.HomeScreen

@Composable
fun RawgApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    showBottomBar = when (navBackStackEntry?.destination?.route) {
        Navigation.Home.route,
        Navigation.Favorite.route,
        -> true
        else -> false
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (showBottomBar) BottomBar(navController)
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Navigation.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = Navigation.Home.route) {
                HomeScreen(
                    navigateToDetail = { movieId ->
                        navController.navigate(
                            Navigation.Detail.createRoute(movieId),
                        )
                    },
                )
            }
            composable(route = Navigation.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { movieId ->
                        navController.navigate(
                            Navigation.Detail.createRoute(movieId),
                        )
                    },
                )
            }
            composable(
                route = Navigation.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                val movieId = it.arguments?.getInt("id")
                DetailScreen(
                    gameId = movieId ?: 0,
                    navController = navController
                )
            }
        }
    }
}
