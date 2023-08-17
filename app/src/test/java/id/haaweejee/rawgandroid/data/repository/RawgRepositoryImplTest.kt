package id.haaweejee.rawgandroid.data.repository

import id.haaweejee.rawgandroid.data.datasource.local.dao.GameDao
import id.haaweejee.rawgandroid.data.datasource.local.table.GameTable
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.detail.DetailGameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.service.RawgRemoteService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class RawgRepositoryImplTest {
    private lateinit var repository: RawgRepositoryImpl
    private val mockService: RawgRemoteService = mock()
    private val dao: GameDao = mock()

    @Before
    fun setup() {
        repository = RawgRepositoryImpl(mockService, dao)
    }

    @Test
    fun `get Detail Game response data`() = runBlocking {
        val gameId = "123"
        val detailGameResponse = DetailGameResponse(name = "Game Name")
        doReturn(detailGameResponse).`when`(mockService).getGamesDetail(gameId)

        val resultFlow = repository.getGamesDetail(gameId)

        val collectedResponse = resultFlow.first()
        assertEquals("Game Name", collectedResponse.name)
    }

    @Test
    fun `insert game to db`() = runBlocking {
        val game = GameTable(id = 1, name = "Example Game")
        repository.insertGameToFavorite(game)
    }

    @Test
    fun `delete game from db`() = runBlocking {
        val gameId = 1
        repository.deleteGameFromFavorite(gameId)
    }

    @Test
    fun `get game by id from db`() = runBlocking {
        // Given
        val gameId = 1
        val mockGame = GameTable(1, "Example Game")

        `when`(dao.getGameById(gameId)).thenReturn(flowOf(mockGame))

        // When
        val result = repository.getGameFavoriteById(gameId).first()

        // Then
        assertEquals(mockGame, result)
    }

    @Test
    fun `get all game from db`() = runBlocking {
        // Given
        val mockGamesList: List<GameTable> = listOf(
            GameTable(1, "Example Game"),
            GameTable(1, "Example Game")
        )
        `when`(dao.getAllFavoriteGames()).thenReturn(flowOf(mockGamesList))

        // When
        val result = repository.getGamesFavorite().first()

        // Then
        assertEquals(mockGamesList, result)
    }
}
