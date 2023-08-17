package id.haaweejee.rawgandroid.data.datasource.remote.service

import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.detail.DetailGameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.gameslist.GamesListResponse

interface RawgRemoteService {

    suspend fun getGamesList(
        query: String? = "",
        page: Int,
        pageSize: Int,
    ): GamesListResponse
    suspend fun getGamesDetail(
        gameId: String,
    ): DetailGameResponse
}