package id.haaweejee.rawgandroid.domain.mapper

import id.haaweejee.rawgandroid.data.datasource.local.table.GameTable
import id.haaweejee.rawgandroid.domain.entities.GameEntities

fun GameEntities.toGameTable() = GameTable(
    id = this.id,
    name = this.name,
    gamesImage = this.gamesImage,
    releasedDate = this.releasedDate,
    rating = this.rating,
    isFavorite = this.isFavorite,
)