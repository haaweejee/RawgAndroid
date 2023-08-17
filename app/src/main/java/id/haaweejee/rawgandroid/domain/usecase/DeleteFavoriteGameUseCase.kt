package id.haaweejee.rawgandroid.domain.usecase

import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import javax.inject.Inject

class DeleteFavoriteGameUseCase @Inject constructor(
    private val repository: RawgRepository,
) {

    suspend operator fun invoke(movieId: Int) {
        repository.deleteGameFromFavorite(
            movieId,
        )
    }
}
