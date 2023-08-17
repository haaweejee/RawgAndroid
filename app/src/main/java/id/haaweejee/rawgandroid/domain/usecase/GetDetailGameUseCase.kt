package id.haaweejee.rawgandroid.domain.usecase

import id.haaweejee.rawgandroid.domain.entities.DetailGameEntities
import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import id.haaweejee.rawgandroid.ui.util.extractTextFromHtml
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailGameUseCase @Inject constructor(
    private val repository: RawgRepository,
    private val getFavoriteGameByIdUseCase: GetFavoriteGameByIdUseCase,
) {

    operator fun invoke(
        gamesId: Int,
    ): Flow<DetailGameEntities> = repository.getGamesDetail(
        gamesId.toString(),
    ).map {
        DetailGameEntities(
            id = it.id ?: 0,
            name = it.name.orEmpty(),
            releasedDate = "Released date: ${it.released.orEmpty()}",
            gamesImage = it.background_image.orEmpty(),
            rating = it.rating ?: 0.0,
            description = it.description.orEmpty().extractTextFromHtml(),
            gamePublisher = it.publishers?.get(0)?.name.orEmpty(),
            isFavorite = getFavoriteGameByIdUseCase(gamesId).first() ?: false
        )
    }
}