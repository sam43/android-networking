package com.sam43.android_networking.models
import com.google.gson.annotations.SerializedName

// Popular Movie List

data class PopularMovies(
    @SerializedName("page")
    val respPage: Int?,
    @SerializedName("results")
    val respResults: List<MovieItem?>?,
    @SerializedName("total_pages")
    val respTotalPages: Int?,
    @SerializedName("total_results")
    val respTotalResults: Int?
)

data class MovieItem(
    @SerializedName("adult")
    val respAdult: Boolean?,
    @SerializedName("backdrop_path")
    val respBackdropPath: String?,
    @SerializedName("genre_ids")
    val respGenreIds: List<Int?>?,
    @SerializedName("id")
    val respId: Int?,
    @SerializedName("original_language")
    val respOriginalLanguage: String?,
    @SerializedName("original_title")
    val respOriginalTitle: String?,
    @SerializedName("overview")
    val respOverview: String?,
    @SerializedName("popularity")
    val respPopularity: Double?,
    @SerializedName("poster_path")
    val respPosterPath: String?,
    @SerializedName("release_date")
    val respReleaseDate: String?,
    @SerializedName("title")
    val respTitle: String?,
    @SerializedName("video")
    val respVideo: Boolean?,
    @SerializedName("vote_average")
    val respVoteAverage: Double?,
    @SerializedName("vote_count")
    val respVoteCount: Int?
)


// Single Movie

data class MovieDetails(
    @SerializedName("adult")
    val respAdult: Boolean?,
    @SerializedName("backdrop_path")
    val respBackdropPath: String?,
    @SerializedName("belongs_to_collection")
    val respBelongsToCollection: Any?,
    @SerializedName("budget")
    val respBudget: Int?,
    @SerializedName("genres")
    val respGenres: List<Genre?>?,
    @SerializedName("homepage")
    val respHomepage: String?,
    @SerializedName("id")
    val respId: Int?,
    @SerializedName("imdb_id")
    val respImdbId: String?,
    @SerializedName("original_language")
    val respOriginalLanguage: String?,
    @SerializedName("original_title")
    val respOriginalTitle: String?,
    @SerializedName("overview")
    val respOverview: String?,
    @SerializedName("popularity")
    val respPopularity: Double?,
    @SerializedName("poster_path")
    val respPosterPath: String?,
    @SerializedName("production_companies")
    val respProductionCompanies: List<ProductionCompany?>?,
    @SerializedName("production_countries")
    val respProductionCountries: List<ProductionCountry?>?,
    @SerializedName("release_date")
    val respReleaseDate: String?,
    @SerializedName("revenue")
    val respRevenue: Int?,
    @SerializedName("runtime")
    val respRuntime: Int?,
    @SerializedName("spoken_languages")
    val respSpokenLanguages: List<SpokenLanguage?>?,
    @SerializedName("status")
    val respStatus: String?,
    @SerializedName("tagline")
    val respTagline: String?,
    @SerializedName("title")
    val respTitle: String?,
    @SerializedName("video")
    val respVideo: Boolean?,
    @SerializedName("vote_average")
    val respVoteAverage: Double?,
    @SerializedName("vote_count")
    val respVoteCount: Int?
)

data class Genre(
    @SerializedName("id")
    val respId: Int?,
    @SerializedName("name")
    val respName: String?
)

data class ProductionCompany(
    @SerializedName("id")
    val respId: Int?,
    @SerializedName("logo_path")
    val respLogoPath: String?,
    @SerializedName("name")
    val respName: String?,
    @SerializedName("origin_country")
    val respOriginCountry: String?
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val respIso31661: String?,
    @SerializedName("name")
    val respName: String?
)

data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val respIso6391: String?,
    @SerializedName("name")
    val respName: String?
)