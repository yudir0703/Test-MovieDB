package com.yudi.test3.api.models.movies


import com.fasterxml.jackson.annotation.JsonProperty


data class MovieDetailResponse(
    @field:JsonProperty("adult")
    var adult: Boolean? = null,
    @field:JsonProperty("backdrop_path")
    var backdropPath: String? = null,
    @field:JsonProperty("belongs_to_collection")
    var belongsToCollection: BelongsToCollection? = null,
    @field:JsonProperty("budget")
    var budget: Int? = null,
    @field:JsonProperty("genres")
    var genres: List<Genre>? = null,
    @field:JsonProperty("homepage")
    var homepage: String? = null,
    @field:JsonProperty("id")
    var id: Int? = null,
    @field:JsonProperty("imdb_id")
    var imdbId: String? = null,
    @field:JsonProperty("original_language")
    var originalLanguage: String? = null,
    @field:JsonProperty("original_title")
    var originalTitle: String? = null,
    @field:JsonProperty("original_name")
    var originalName: String? = null,
    @field:JsonProperty("overview")
    var overview: String? = null,
    @field:JsonProperty("popularity")
    var popularity: Double? = null,
    @field:JsonProperty("poster_path")
    var posterPath: String? = null,
    @field:JsonProperty("production_companies")
    var productionCompanies: List<ProductionCompany>? = null,
    @field:JsonProperty("production_countries")
    var productionCountries: List<ProductionCountry>? = null,
    @field:JsonProperty("release_date")
    var releaseDate: String? = null,
    @field:JsonProperty("revenue")
    var revenue: Int? = null,
    @field:JsonProperty("runtime")
    var runtime: Int? = null,
    @field:JsonProperty("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>? = null,
    @field:JsonProperty("status")
    var status: String? = null,
    @field:JsonProperty("tagline")
    var tagline: String? = null,
    @field:JsonProperty("title")
    var title: String? = null,
    @field:JsonProperty("video")
    var video: Boolean? = null,
    @field:JsonProperty("vote_average")
    var voteAverage: Double? = null,
    @field:JsonProperty("vote_count")
    var voteCount: Int? = null
)