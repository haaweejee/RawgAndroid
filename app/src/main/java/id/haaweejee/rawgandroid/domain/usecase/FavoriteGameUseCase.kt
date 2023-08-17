package id.haaweejee.rawgandroid.domain.usecase

import id.haaweejee.rawgandroid.domain.entities.FavoriteEntities
import id.haaweejee.rawgandroid.domain.entities.GameEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteGameUseCase @Inject constructor(
    val insert: InsertGameToFavoriteUseCase,
    val delete: DeleteFavoriteGameUseCase,
    val get: GetFavoriteGameByIdUseCase,
) {

    suspend operator fun invoke(
        data: GameEntities,
    ): Flow<FavoriteEntities> {
        val bookmarked = get(data.id).first()
        return if (bookmarked == true) {
            delete(data.id)
            flow {
                emit(
                    FavoriteEntities(
                        "Unbookmark Movies",
                        false
                    )
                )
            }
        } else {
            insert(data)
            flow {
                emit(
                    FavoriteEntities(
                        "Bookmark Movies",
                    true
                    )
                )
            }
        }
    }
}
