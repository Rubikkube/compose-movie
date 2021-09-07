package com.rubikkube.composemovie.data.repository

import android.util.Log
import com.rubikkube.composemovie.data.remote.ApiService
import com.rubikkube.composemovie.data.remote.DataSource
import com.rubikkube.composemovie.data.remote.responses.MovieDetailsResponse
import com.rubikkube.composemovie.data.remote.responses.MoviesResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

@DelicateCoroutinesApi
class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): AppRepository {
    override suspend fun getUpComingMovies(apiKey: String): Flow<DataSource<MoviesResponse>> {
        return flow {
            try {
                val result = apiService.getUpComingMovies(apiKey = apiKey)
                if(result.results.isEmpty()) {
                    emit(DataSource.empty<MoviesResponse>())
                } else {
                    emit(DataSource.success(result))
                }
            } catch (e: Exception) {
                if(e is IOException) {
                    emit(DataSource.error("No Internet Connection", null))
                } else {
                    emit(DataSource.error("Something went wrong...", null))
                }
            }
        }
    }

    override suspend fun getPlayingNowMovies(apiKey: String): Flow<DataSource<MoviesResponse>> {
        return flow {
            try {
                val result = apiService.getNowPlayingMovies(apiKey = apiKey)
                if(result.results.isEmpty()) {
                    emit(DataSource.empty<MoviesResponse>())
                } else {
                    emit(DataSource.success(result))
                }
            } catch (e: Exception) {
                if(e is IOException) {
                    emit(DataSource.error("No Internet Connection", null))
                } else {
                    Log.d("API ERROR", e.localizedMessage)
                    emit(DataSource.error("Something went wrong...", null))
                }
            }
        }
    }

    override suspend fun getMoviesDetails(apiKey: String, movieId: String): Flow<DataSource<MovieDetailsResponse>> {
        return flow {
            try {
                val result = apiService.getMovieDetails(apiKey = apiKey, movieId)
                emit(DataSource.success(result))
            } catch (e: Exception) {
                if(e is IOException) {
                    emit(DataSource.error("No Internet Connection", null))
                } else {
                    emit(DataSource.error("Something went wrong...", null))
                }
            }
        }
    }

}