package com.yudi.test3.api.models.movies


import com.fasterxml.jackson.annotation.JsonProperty

data class ProductionCountry(
    @field:JsonProperty("iso_3166_1")
    var iso31661: String? = null,
    @field:JsonProperty("name")
    var name: String? = null
)