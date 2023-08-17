package id.haaweejee.rawgandroid.ui.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import id.haaweejee.rawgandroid.ui.navigation.Navigation
import id.haaweejee.rawgandroid.ui.navigation.model.NavigationItem
import id.haaweejee.rawgandroid.ui.theme.DarkBlueGrey

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                navigation = Navigation.Home,
            ),
            NavigationItem(
                title = "Favorite",
                icon = Icons.Default.Favorite,
                navigation = Navigation.Favorite,
            ),
        )
        BottomNavigation(
            backgroundColor = DarkBlueGrey
        ) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                        )
                    },
                    label = {
                        Text(item.title)
                    },
                    selected = currentRoute == item.navigation.route,
                    onClick = {
                        navController.navigate(item.navigation.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                )
            }
        }
    }
}
