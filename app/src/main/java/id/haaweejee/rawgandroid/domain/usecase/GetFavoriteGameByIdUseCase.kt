package id.haaweejee.rawgandroid.domain.usecase

import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteGameByIdUseCase @Inject constructor(
    private val repository: RawgRepository,
) {

    operator fun invoke(id: Int): Flow<Boolean?> =
        repository.getGameFavoriteById(id).map {
            if (it != null) {
                it.isFavorite
            } else {
                false
            }
        }
}
