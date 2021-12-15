package com.android.movieapp.ui.detail

import com.android.movieapp.MovieDetail

sealed class DetailState {
    object Processing: DetailState()
    object Failed: DetailState()
    data class Success(val movieDetail: MovieDetail): DetailState()

}
