package id.haaweejee.rawgandroid.domain.usecase

import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteGameListUseCase @Inject constructor(
    private val repository: RawgRepository,
) {
    operator fun invoke(): Flow<List<GameEntities>> = repository.getGamesFavorite().map {
        it.map { game ->
            GameEntities(
                id = game.id ?: 0,
                name = game.name.orEmpty(),
                gamesImage = game.gamesImage.orEmpty(),
                rating = game.rating ?: 0.0,
                releasedDate = "Released Date: ${game.releasedDate.orEmpty()}",
                isFavorite = true
            )
        }
    }
}