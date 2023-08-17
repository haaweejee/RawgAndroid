package id.haaweejee.rawgandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.haaweejee.rawgandroid.data.datasource.local.dao.GameDao
import id.haaweejee.rawgandroid.data.datasource.local.table.GameTable
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.detail.DetailGameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.gameslist.GameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.service.RawgRemoteService
import id.haaweejee.rawgandroid.data.paging.GamesPagingSource
import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RawgRepositoryImpl @Inject constructor(
    private val service: RawgRemoteService,
    private val dao: GameDao,
) : RawgRepository {

    override fun getGamesList(
        query: String?,
    ): Flow<PagingData<GameResponse>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            GamesPagingSource(service, 10, query)
        },
    ).flow

    override fun getGamesDetail(gameId: String): Flow<DetailGameResponse> = flow {
        emit(service.getGamesDetail(gameId))
    }

    override suspend fun insertGameToFavorite(game: GameTable) =
        dao.insertGame(game)

    override suspend fun deleteGameFromFavorite(id: Int) =
        dao.deleteGameById(id)

    override fun getGameFavoriteById(id: Int): Flow<GameTable?> =
        dao.getGameById(id)

    override fun getGamesFavorite(): Flow<List<GameTable>> =
        dao.getAllFavoriteGames()
}
