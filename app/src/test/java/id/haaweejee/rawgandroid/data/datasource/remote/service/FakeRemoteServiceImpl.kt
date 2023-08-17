package id.haaweejee.rawgandroid.data.datasource.remote.service

import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.detail.DetailGameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.gameslist.GamesListResponse

class FakeRemoteServiceImpl : RawgRemoteService {
    override suspend fun getGamesList(query: String?, page: Int, pageSize: Int): GamesListResponse =
        GamesListResponse(
            count = 10,
            results = listOf(),
        )

    override suspend fun getGamesDetail(gameId: String): DetailGameResponse {
        return DetailGameResponse(
            name = "GTA",
        )
    }
}
