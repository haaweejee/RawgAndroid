package id.haaweejee.rawgandroid.data.datasource.remote.dto.response.gameslist

data class GamesListResponse(
    val count: Int? = 0,
    val description: String? = "",
    val filters: Filters? = null,
    val next: String? = "",
    val nofollow: Boolean? = false,
    val nofollow_collections: List<String>? = listOf(),
    val noindex: Boolean? = false,
    val previous: Any? = null,
    val results: List<GameResponse>,
    val seo_description: String? = "",
    val seo_h1: String? = "",
    val seo_keywords: String? = "",
    val seo_title: String? = "",
)
