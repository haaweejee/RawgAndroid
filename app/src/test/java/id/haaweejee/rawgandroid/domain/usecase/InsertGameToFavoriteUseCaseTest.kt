package id.haaweejee.rawgandroid.domain.usecase

import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.domain.mapper.toGameTable
import id.haaweejee.rawgandroid.domain.repository.RawgRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock

class InsertGameToFavoriteUseCaseTest {

    private val repository: RawgRepository = mock()

    private lateinit var useCase: InsertGameToFavoriteUseCase

    @Before
    fun setup() {
        useCase = InsertGameToFavoriteUseCase(repository)
    }

    @Test
    fun `test insert game to favorite usecase`() = runBlocking {
        // Given
        val mockModel = GameEntities(
            id = 1,
            name = "Example Game",
            gamesImage = "https://example.com/image.jpg",
            rating = 9.5,
            releasedDate = "Released Date: 2021-08-17",
            isFavorite = true
        )
        val mockGameTable = mockModel.toGameTable()

        // When
        useCase(mockModel)

        // Then
        verify(repository).insertGameToFavorite(eq(mockGameTable))
    }
}