package com.android.movieapp.ui.home

import com.android.movieapp.Movie
import com.android.movieapp.MovieDetail

sealed class HomeState {
    object Processing: HomeState()
    object Failed: HomeState()
    data class Success(val movieDetail: MovieDetail): HomeState()

    data class Movies(val movies: List<Movie>): HomeState()
}
