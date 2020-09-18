package com.yudi.test3.api.models.movies


import com.fasterxml.jackson.annotation.JsonProperty

data class Genre(
    @field:JsonProperty("id")
    var id: Int? = null,
    @field:JsonProperty("name")
    var name: String? = null
)