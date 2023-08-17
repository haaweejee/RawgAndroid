package id.haaweejee.rawgandroid.domain.repository

import androidx.paging.PagingData
import id.haaweejee.rawgandroid.data.datasource.local.table.GameTable
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.detail.DetailGameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.gameslist.GameResponse
import kotlinx.coroutines.flow.Flow

interface RawgRepository {

    fun getGamesList(
        query: String? = "",
    ): Flow<PagingData<GameResponse>>

    fun getGamesDetail(
        gameId: String,
    ): Flow<DetailGameResponse>

    suspend fun insertGameToFavorite(game: GameTable)

    suspend fun deleteGameFromFavorite(id: Int)

    fun getGameFavoriteById(id: Int): Flow<GameTable?>

    fun getGamesFavorite(): Flow<List<GameTable>>
}
