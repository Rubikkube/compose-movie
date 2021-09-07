package com.rubikkube.composemovie.data.repository

import com.rubikkube.composemovie.data.remote.DataSource
import com.rubikkube.composemovie.data.remote.responses.MovieDetailsResponse
import com.rubikkube.composemovie.data.remote.responses.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getUpComingMovies(apiKey: String): Flow<DataSource<MoviesResponse>>
    suspend fun getPlayingNowMovies(apiKey: String): Flow<DataSource<MoviesResponse>>
    suspend fun getMoviesDetails(apiKey: String, movieId: String): Flow<DataSource<MovieDetailsResponse>>
}