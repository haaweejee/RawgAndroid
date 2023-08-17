package id.haaweejee.rawgandroid.data.datasource.remote.service

import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.detail.DetailGameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.gameslist.GamesListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Singleton

@Singleton
class RawgRemoteServiceImpl(
    private val service: HttpClient,
) : RawgRemoteService {

    val baseUrl = "https://api.rawg.io/api"
    val gamesList = "/games"
    val gameDetail = "/games/"
    val apiKey = "c62c39054b004a8b9b7bff52f7362d64"
    override suspend fun getGamesList(query: String?, page: Int, pageSize: Int): GamesListResponse {
        return service.get {
            url(baseUrl + gamesList)
            parameter("key", apiKey)
            parameter("search", query)
            parameter("page", page)
            parameter("page_size", pageSize)
        }.body()
    }

    override suspend fun getGamesDetail(gameId: String): DetailGameResponse {
        return service.get {
            url(baseUrl + gameDetail + gameId)
            parameter("key", apiKey)
        }.body()
    }
}
