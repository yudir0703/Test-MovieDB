package com.yudi.test3.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yudi.test3.api.interfaces.ApiCallBack
import com.yudi.test3.api.models.movies.MovieDetailResponse
import com.yudi.test3.api.models.trending.DataTrending
import com.yudi.test3.api.models.trending.TrendingResponse
import com.yudi.test3.api.repository.ExploreRepositories
import com.yudi.test3.app.base.BaseViewModel

/**
 * @author Yudi Rahmat
 */

class ExploreViewModel(
    private val exploreRepositories: ExploreRepositories) : BaseViewModel<ExploreRepositories>(exploreRepositories) {

    private var movieDetailData: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    private val movieDetailDataLive: LiveData<MovieDetailResponse> = movieDetailData

    private var trendingData: MutableLiveData<List<DataTrending?>?> = MutableLiveData()
    private var trendingDataLive: LiveData<List<DataTrending?>?> = trendingData

    private var tredingTotal: MutableLiveData<Int> = MutableLiveData()
    private val trendingTotalLive: LiveData<Int> = tredingTotal
    
    fun loadTrending(page: Int) {
        if(page <= 1) trendingData.value = null
        exploreRepositories.loadTrending(page,
            object : ApiCallBack<TrendingResponse> {
                override fun onError(error: Throwable) {
                    processError(error)
                }

                override fun onSucess(response: TrendingResponse) {
                    trendingData.value = response.data
                    tredingTotal.value = response.totalResults
                }
            })
    }

    fun loadUpcoming(page: Int) {
        if(page <= 1) trendingData.value = null
        exploreRepositories.loadUpcoming(page,
            object : ApiCallBack<TrendingResponse> {
                override fun onError(error: Throwable) {
                    processError(error)
                }

                override fun onSucess(response: TrendingResponse) {
                    trendingData.value = response.data
                    tredingTotal.value = response.totalResults
                }
            })
    }

    fun loadNowPlaying(page: Int) {
        if(page <= 1) trendingData.value = null
        exploreRepositories.loadNowPlaying(page,
            object : ApiCallBack<TrendingResponse> {
                override fun onError(error: Throwable) {
                    processError(error)
                }

                override fun onSucess(response: TrendingResponse) {
                    trendingData.value = response.data
                    tredingTotal.value = response.totalResults
                }
            })
    }

    fun loadPopular(page: Int) {
        if(page <= 1) trendingData.value = null
        exploreRepositories.loadPopular(page,
            object : ApiCallBack<TrendingResponse> {
                override fun onError(error: Throwable) {
                    processError(error)
                }

                override fun onSucess(response: TrendingResponse) {
                    trendingData.value = response.data
                    tredingTotal.value = response.totalResults
                }
            })
    }

    fun loadDetailMovie(movieId: Int) {
        exploreRepositories.loadDetailMovie(movieId,
            object : ApiCallBack<MovieDetailResponse> {
                override fun onError(error: Throwable) {
                    processError(error)
                }

                override fun onSucess(response: MovieDetailResponse) {
                    movieDetailData.value = response
                }
            })
    }

    fun searchMovies(query: String?, page: Int) {
        if(page <= 1) trendingData.value = null
        exploreRepositories.searchMovies(query, page,
            object : ApiCallBack<TrendingResponse> {
                override fun onError(error: Throwable) {
                    processError(error)
                }

                override fun onSucess(response: TrendingResponse) {
                    trendingData.value = response.data
                    tredingTotal.value = response.totalResults
                }
            })
    }

    fun getTrendingList(): LiveData<List<DataTrending?>?> {
        return trendingDataLive
    }

    fun getMovieDetail(): LiveData<MovieDetailResponse> {
        return movieDetailDataLive
    }

    fun getTrendingTotal(): LiveData<Int> {
        return trendingTotalLive
    }
}