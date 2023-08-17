package id.haaweejee.rawgandroid.domain.usecase


import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.domain.mapper.toGameTable
import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import javax.inject.Inject

class InsertGameToFavoriteUseCase @Inject constructor(
    private val repository: RawgRepository,
) {

    suspend operator fun invoke(game: GameEntities) {
        repository.insertGameToFavorite(
            game.toGameTable(),
        )
    }
}
