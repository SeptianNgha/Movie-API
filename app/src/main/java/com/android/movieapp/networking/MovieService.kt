package com.android.movieapp.networking

import com.android.movieapp.MovieDetail
import com.android.movieapp.MovieResponse
import retrofit2.Call

class MovieService : MovieApi {

    private val movieApi: MovieApi =
        MovieRequest.retrofitInstance!!.create(MovieApi::class.java)

    override fun getMovieByTitle(judul: String): Call<MovieDetail> {
        return movieApi.getMovieByTitle(title = judul)
    }

    override fun getMovies(title: String): Call<MovieResponse> {
        return movieApi.getMovies(title)
    }
}