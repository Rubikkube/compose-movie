package com.rubikkube.composemovie.data.remote.responses

data class MoviesResponse(
    val results: List<MovieModel>
)

data class MovieModel(
    var id: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var poster_path: String,
    var vote_average: String
)