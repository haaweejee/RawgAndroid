package id.haaweejee.rawgandroid.domain.entities

data class GameEntities(
    val id: Int,
    val name: String,
    val releasedDate: String,
    val gamesImage: String,
    val rating: Double,
    val isFavorite: Boolean,
)
