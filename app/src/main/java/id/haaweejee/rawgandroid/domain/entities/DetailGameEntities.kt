package id.haaweejee.rawgandroid.domain.entities

data class DetailGameEntities(
    val id: Int,
    val name: String,
    val releasedDate: String,
    val gamesImage: String,
    val rating: Double,
    val description: String,
    val gamePublisher: String,
    val isFavorite: Boolean
)
