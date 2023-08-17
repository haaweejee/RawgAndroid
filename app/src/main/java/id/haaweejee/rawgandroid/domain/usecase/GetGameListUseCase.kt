package id.haaweejee.rawgandroid.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetGameListUseCase @Inject constructor(
    private val repository: RawgRepository
) {
    operator fun invoke(
        query: String? = "",
    ): Flow<PagingData<GameEntities>> = repository.getGamesList(
        query = query,
    ).map {
        it.map { game ->
            GameEntities(
                id = game.id ?: 0,
                name = game.name.orEmpty(),
                gamesImage = game.background_image.orEmpty(),
                rating = game.rating ?: 0.0,
                releasedDate = "Released Date: ${game.released.orEmpty()}",
                isFavorite = false
            )
        }
    }
}