package id.haaweejee.rawgandroid.data.datasource.remote.service

import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.detail.DetailGameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.gameslist.GamesListResponse
import junit.framework.TestCase
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito

class RawgRemoteServiceImplTest {
    private val service: RawgRemoteService = Mockito.mock()
    private val fakeService: FakeRemoteServiceImpl = FakeRemoteServiceImpl()

    @Test
    fun `should fetch games list with success response`(): TestResult = runTest {
        val successResponse = GamesListResponse(
            count = 10,
            next = "",
            previous = "",
            results = listOf(),
        )
        Mockito.`when`(
            service.getGamesList("", 1, 1),
        ).thenReturn(successResponse)

        val actual = fakeService.getGamesList("", 1, 10).count
        TestCase.assertEquals(10, actual)
    }

    @Test
    fun `should fetch detail games with success response`(): TestResult = runTest {
        val successResponse = DetailGameResponse(
            name = "GTA",
        )

        Mockito.`when`(
            service.getGamesDetail(""),
        ).thenReturn(successResponse)

        val actual = fakeService.getGamesDetail("").name
        TestCase.assertEquals("GTA", actual)
    }
}
