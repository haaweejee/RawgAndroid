package id.haaweejee.rawgandroid.domain.usecase

import id.haaweejee.rawgandroid.data.datasource.local.table.GameTable
import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

class GetFavoriteGameListUseCaseTest {
    private val repository: RawgRepository = mock()

    private lateinit var useCase: GetFavoriteGameListUseCase

    @Before
    fun setup() {
        useCase = GetFavoriteGameListUseCase(repository)
    }

    @Test
    fun `get favorite list Data`() = runBlocking {
        // Given
        val mockModel = GameTable(
            id = 1,
            name = "Example Game",
            gamesImage = "https://example.com/image.jpg",
            rating = 9.5,
            releasedDate = "2021-08-17",
            isFavorite = true
        )
        val mockGameList = listOf(mockModel)
        `when`(repository.getGamesFavorite()).thenReturn(flowOf(mockGameList))

        // When
        val resultFlow = useCase()

        val expected = listOf(
            GameEntities(
                id = 1,
                name = "Example Game",
                gamesImage = "https://example.com/image.jpg",
                rating = 9.5,
                releasedDate = "Released Date: 2021-08-17",
                isFavorite = true
            )
        )
        // Then
        resultFlow.collect { result ->
            assertEquals(expected[0].name, result[0].name)
            assertEquals(expected[0].id, result[0].id)
            assertEquals(expected[0].gamesImage, result[0].gamesImage)
            assertEquals(expected[0].rating, result[0].rating)
            assertEquals(expected[0].isFavorite, result[0].isFavorite)
            assertEquals(expected[0].releasedDate, result[0].releasedDate)

        }
    }
}