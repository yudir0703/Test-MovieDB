package com.yudi.test3.service.api

import android.content.Context
import com.yudi.test3.api.models.movies.MovieDetailResponse
import com.yudi.test3.api.models.trending.TrendingResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Yudi Rahmat
 */

interface APIInterface {

    companion object {
        operator fun invoke(baseUrl: String, mContext: Context): APIInterface {
            val baseService =
                APIService(
                    baseUrl,
                    mContext
                )

            return baseService.retrofit.create(APIInterface::class.java)
        }
    }

    @GET("trending/{media_type}/{time_window}")
    fun loadTrending(@Path("media_type") mediaType: String, @Path("time_window") timeWindow: String, @Query("api_key") apiKey: String, @Query("page") page: Int): Observable<TrendingResponse>

    @GET("movie/upcoming")
    fun upcoming(@Query("language") language: String?, @Query("api_key") apiKey: String, @Query("page") page: Int): Observable<TrendingResponse>

    @GET("movie/now_playing")
    fun nowPlaying(@Query("language") language: String?, @Query("api_key") apiKey: String, @Query("page") page: Int): Observable<TrendingResponse>

    @GET("movie/popular")
    fun popular(@Query("language") language: String?, @Query("api_key") apiKey: String, @Query("page") page: Int): Observable<TrendingResponse>

    @GET("movie/{movie_id}")
    fun loadDetailMovie(@Path("movie_id") movieIdd: Int, @Query("api_key") apiKey: String, @Query("language") language: String): Observable<MovieDetailResponse>

    @GET("search/movie")
    fun searchMovies(@Query("api_key") apiKey: String, @Query("language") language: String?, @Query("query") query: String?, @Query("include_adult") includeAdult: Boolean, @Query("page") page: Int): Observable<TrendingResponse>

}

