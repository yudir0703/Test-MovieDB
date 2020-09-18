package com.yudi.test3.api.models.trending


import com.fasterxml.jackson.annotation.JsonProperty

data class TrendingResponse(
    @field:JsonProperty("results")
    var data: List<DataTrending>? = null,
    @field:JsonProperty("page")
    var page: Int? = null,
    @field:JsonProperty("total_pages")
    var totalPages: Int? = null,
    @field:JsonProperty("total_results")
    var totalResults: Int? = null,

    // Upcoming
    @field:JsonProperty("dates")
    var dates: Dates? = null
)