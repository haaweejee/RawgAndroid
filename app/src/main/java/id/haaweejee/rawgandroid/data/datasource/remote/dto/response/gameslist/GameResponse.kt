package id.haaweejee.rawgandroid.data.datasource.remote.dto.response.gameslist

data class GameResponse(
    val added: Int? = 0,
    val added_by_status: AddedByStatus? = null,
    val background_image: String? = "",
    val clip: Any? = null,
    val dominant_color: String? = "",
    val esrb_rating: EsrbRating? = null,
    val genres: List<Genre>? = emptyList(),
    val id: Int? = 0,
    val metacritic: Int? = 0,
    val name: String? = "",
    val parent_platforms: List<ParentPlatform>? = emptyList(),
    val platforms: List<PlatformX>? = emptyList(),
    val playtime: Int? = 0,
    val rating: Double? = 0.0,
    val rating_top: Int? = 0,
    val ratings: List<Rating>? = emptyList(),
    val ratings_count: Int? = 0,
    val released: String? = "",
    val reviews_count: Int? = 0,
    val reviews_text_count: Int? = 0,
    val saturated_color: String? = "",
    val short_screenshots: List<ShortScreenshot>? = emptyList(),
    val slug: String? = "",
    val stores: List<Store>? = emptyList(),
    val suggestions_count: Int? = 0,
    val tags: List<Tag>? = emptyList(),
    val tba: Boolean? = false,
    val updated: String? = "",
    val user_game: Any? = null
)