package id.haaweejee.rawgandroid.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.haaweejee.rawgandroid.data.datasource.remote.dto.response.gameslist.GameResponse
import id.haaweejee.rawgandroid.data.datasource.remote.service.RawgRemoteService

class GamesPagingSource(
    private val service: RawgRemoteService,
    private val pageSize: Int,
    private val query: String? = "",
) : PagingSource<Int, GameResponse>() {

    override fun getRefreshKey(state: PagingState<Int, GameResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameResponse> =
        try {
            val page = params.key ?: 1
            val response = service.getGamesList(query, page, pageSize)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}