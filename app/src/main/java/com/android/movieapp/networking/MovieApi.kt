package com.android.movieapp.networking

import com.android.movieapp.MovieDetail
import com.android.movieapp.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    // http://www.omdbapi.com/?apikey=[yourkey]&

    @GET("?apikey=6c154ba6")
    fun getMovieByTitle(
        @Query("t") title : String
    ) : Call<MovieDetail>


    @GET("?apikey=6c154ba6")
    fun getMovies(
        @Query("s") title : String
    ) : Call<MovieResponse>
}