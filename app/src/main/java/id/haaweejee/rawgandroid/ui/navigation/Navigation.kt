package id.haaweejee.rawgandroid.ui.navigation

sealed class Navigation(val route: String) {
    object Home : Navigation("home")
    object Favorite : Navigation("account")
    object Detail : Navigation("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}
