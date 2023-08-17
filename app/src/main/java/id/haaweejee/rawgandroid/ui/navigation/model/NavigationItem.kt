package id.haaweejee.rawgandroid.ui.navigation.model

import androidx.compose.ui.graphics.vector.ImageVector
import id.haaweejee.rawgandroid.ui.navigation.Navigation

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val navigation: Navigation,
)
