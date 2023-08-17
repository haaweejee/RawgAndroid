package id.haaweejee.rawgandroid.domain.usecase

import id.haaweejee.rawgandroid.data.datasource.local.table.GameTable
import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

class GetFavoriteGameByIdUseCaseTest {

    private val repository: RawgRepository = mock()

    private lateinit var useCase: GetFavoriteGameByIdUseCase

    @Before
    fun setUp() {
        useCase = GetFavoriteGameByIdUseCase(repository)
    }


    @Test
    fun `check is game is favorite`() = runTest {
        // Given
        val gameId = 1
        val mockGameTable = GameTable(
            id = 1,
            name = "Example Game",
            gamesImage = "https://example.com/image.jpg",
            rating = 9.5,
            releasedDate = "2021-08-17",
            isFavorite = true
        )
        `when`(repository.getGameFavoriteById(gameId)).thenReturn(flowOf(mockGameTable))

        // When
        val result = useCase(gameId)

        // Then
        result.collect {
            assertTrue(result.first() == true)
        }
    }

    @Test
    fun `check is game is not favorite`() = runTest {
        // Given
        val gameId = 1

        `when`(repository.getGameFavoriteById(gameId)).thenReturn(flowOf(null))

        // When
        val result = useCase(gameId)

        // Then
        result.collect {
            assertTrue(result.first() == false)
        }
    }
}