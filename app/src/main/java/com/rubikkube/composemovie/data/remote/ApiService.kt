package com.rubikkube.composemovie.data.remote

import com.rubikkube.composemovie.data.remote.responses.CastResponse
import com.rubikkube.composemovie.data.remote.responses.MovieDetailsResponse
import com.rubikkube.composemovie.data.remote.responses.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(@Query("api_key") apiKey: String): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: String,
        @Query("api_key") apiKey: String,
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movie_id: String,
        @Query("api_key") apiKey: String
    ): CastResponse

}