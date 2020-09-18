package com.yudi.test3.api.models.movies


import com.fasterxml.jackson.annotation.JsonProperty

data class SpokenLanguage(
    @field:JsonProperty("iso_639_1")
    var iso6391: String? = null,
    @field:JsonProperty("name")
    var name: String? = null
)