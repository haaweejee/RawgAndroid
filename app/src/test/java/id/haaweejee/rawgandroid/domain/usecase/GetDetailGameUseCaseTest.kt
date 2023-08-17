package id.haaweejee.rawgandroid.domain.usecase

import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.detail.DetailGameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.detail.Publisher
import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetDetailGameUseCaseTest {

    private lateinit var useCase: GetDetailGameUseCase
    private val getFavoriteGameByIdUseCase: GetFavoriteGameByIdUseCase = mock()
    private val mockRepository: RawgRepository = mock()

    @Before
    fun setup() {
        useCase = GetDetailGameUseCase(mockRepository, getFavoriteGameByIdUseCase)
    }

    @Test
    fun `invoke should return mapped DetailGameEntities`() = runBlocking {
        val gameId = 123
        val detailGameResponse = DetailGameResponse(
            id = gameId,
            name = "Game Name",
            released = "2023-08-17",
            background_image = "image_url",
            rating = 9.5,
            description = "Game Description",
            publishers = listOf(Publisher(name = "Publisher Name"))
        )
        whenever(mockRepository.getGamesDetail(gameId.toString())).thenReturn(flowOf(detailGameResponse))


        whenever(getFavoriteGameByIdUseCase(gameId)).thenReturn(flowOf(true))

        val resultFlow = useCase.invoke(gameId)

        resultFlow.collect { collectedDetailGameEntities ->
            assertEquals(gameId, collectedDetailGameEntities.id)
            assertEquals("Game Name", collectedDetailGameEntities.name)
            assertEquals("Released date: 2023-08-17", collectedDetailGameEntities.releasedDate)
            assertEquals("image_url", collectedDetailGameEntities.gamesImage)
            assertEquals(9.5, collectedDetailGameEntities.rating, 0.01)
            assertEquals("Game Description", collectedDetailGameEntities.description)
            assertEquals("Publisher Name", collectedDetailGameEntities.gamePublisher)
            assertEquals(true, collectedDetailGameEntities.isFavorite)
        }
    }
}