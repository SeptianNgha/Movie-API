package com.android.movieapp.networking

import com.android.movieapp.MovieDetail
import com.android.movieapp.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository {

    private val movieService = MovieService()

    suspend fun movieByTitle (title: String) : MovieDetail =
        withContext(Dispatchers.IO) {

            // Check if there's internet connection
            val execute = movieService.getMovieByTitle(title).execute()
            if (execute.isSuccessful) {
                return@withContext execute.body() ?: MovieDetail()
                }
            else {
                return@withContext MovieDetail()
                }
        }


    suspend fun movies(title: String) : MovieResponse = withContext(Dispatchers.IO) {

        val execute = movieService.getMovies(title).execute()

        if (execute.isSuccessful) {
            return@withContext execute.body() ?: MovieResponse()
            }

        else {
            return@withContext MovieResponse()
        }
    }


}