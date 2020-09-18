package com.yudi.test3.api.models.movies

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Yudi Rahmat
 */
data class BelongsToCollection (
    @field:JsonProperty("backdrop_path")
    var backdropPath: String? = null,
    @field:JsonProperty("id")
    var id: Int? = null,
    @field:JsonProperty("name")
    var name: String? = null,
    @field:JsonProperty("poster_path")
    var posterPath: String? = null
)