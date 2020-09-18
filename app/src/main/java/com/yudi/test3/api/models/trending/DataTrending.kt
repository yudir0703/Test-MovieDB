package com.yudi.test3.api.models.trending


import com.fasterxml.jackson.annotation.JsonProperty

data class DataTrending(
    @field:JsonProperty("adult")
    var adult: Boolean? = null,
    @field:JsonProperty("backdrop_path")
    var backdropPath: String? = null,
    @field:JsonProperty("first_air_date")
    var firstAirDate: String? = null,
    @field:JsonProperty("genre_ids")
    var genreIds: List<Int>? = null,
    @field:JsonProperty("id")
    var id: Int? = null,
    @field:JsonProperty("media_type")
    var mediaType: String? = null,
    @field:JsonProperty("name")
    var name: String? = null,
    @field:JsonProperty("origin_country")
    var originCountry: List<String>? = null,
    @field:JsonProperty("original_language")
    var originalLanguage: String? = null,
    @field:JsonProperty("original_name")
    var originalName: String? = null,
    @field:JsonProperty("original_title")
    var originalTitle: String? = null,
    @field:JsonProperty("overview")
    var overview: String? = null,
    @field:JsonProperty("popularity")
    var popularity: Double? = null,
    @field:JsonProperty("poster_path")
    var posterPath: String? = null,
    @field:JsonProperty("release_date")
    var releaseDate: String? = null,
    @field:JsonProperty("title")
    var title: String? = null,
    @field:JsonProperty("video")
    var video: Boolean? = null,
    @field:JsonProperty("vote_average")
    var voteAverage: Double? = null,
    @field:JsonProperty("vote_count")
    var voteCount: Int? = null
)