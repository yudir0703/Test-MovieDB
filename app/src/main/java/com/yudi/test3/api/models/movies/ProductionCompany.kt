package com.yudi.test3.api.models.movies


import com.fasterxml.jackson.annotation.JsonProperty

data class ProductionCompany(
    @field:JsonProperty("id")
    var id: Int? = null,
    @field:JsonProperty("logo_path")
    var logoPath: String?? = null,
    @field:JsonProperty("name")
    var name: String? = null,
    @field:JsonProperty("origin_country")
    var originCountry: String? = null
)