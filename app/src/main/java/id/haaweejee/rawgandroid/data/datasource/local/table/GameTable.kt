package id.haaweejee.rawgandroid.data.datasource.local.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_list")
data class GameTable(
    @PrimaryKey
    val id: Int? = 0,
    val name: String? = "",
    val releasedDate: String? = "",
    val gamesImage: String? = "",
    val rating: Double? = 0.0,
    val isFavorite: Boolean? = false
)
