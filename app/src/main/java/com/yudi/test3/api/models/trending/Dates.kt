package com.yudi.test3.api.models.trending


import com.fasterxml.jackson.annotation.JsonProperty

data class Dates(
    @field:JsonProperty("maximum")
    var maximum: String? = null,
    @field:JsonProperty("minimum")
    var minimum: String? = null
)