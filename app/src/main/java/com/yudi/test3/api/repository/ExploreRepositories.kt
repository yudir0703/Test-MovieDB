package com.yudi.test3.api.repository

import com.yudi.test3.api.base.BaseRepository
import com.yudi.test3.api.interfaces.ApiCallBack
import com.yudi.test3.api.models.movies.MovieDetailResponse
import com.yudi.test3.api.models.trending.TrendingResponse
import com.yudi.test3.app.common.Constant
import com.yudi.test3.service.api.APIInterface
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Yudi Rahmat
 */

class ExploreRepositories(service: APIInterface, compositeDisposable: CompositeDisposable) : BaseRepository(service, compositeDisposable) {

    fun loadTrending(page: Int, callback: ApiCallBack<TrendingResponse>) {
        fetchData(service.loadTrending("all", "day", Constant.APIKEY, page), callback)
    }

    fun loadUpcoming(page: Int, callback: ApiCallBack<TrendingResponse>) {
        fetchData(service.upcoming(null, Constant.APIKEY, page), callback)
    }

    fun loadNowPlaying(page: Int, callback: ApiCallBack<TrendingResponse>) {
        fetchData(service.nowPlaying(null, Constant.APIKEY, page), callback)
    }

    fun loadPopular(page: Int, callback: ApiCallBack<TrendingResponse>) {
        fetchData(service.popular(null, Constant.APIKEY, page), callback)
    }

    fun loadDetailMovie(movieId: Int, callback: ApiCallBack<MovieDetailResponse>) {
        fetchData(service.loadDetailMovie(movieId, Constant.APIKEY, "en-US"), callback)
    }

    fun searchMovies(query: String?, page: Int, callback: ApiCallBack<TrendingResponse>) {
        fetchData(service.searchMovies(Constant.APIKEY, null, query, false, page), callback)
    }
}