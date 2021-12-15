package com.android.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.movieapp.networking.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    private var _homeState = MutableLiveData<HomeState>()
    fun homeState() : LiveData<HomeState> = _homeState

    fun getMovieByTitle (title: String) {
        viewModelScope.launch {
            val movieResponse = movieRepository.movieByTitle(title)

            if (movieResponse.imdbID == "") {
                _homeState.value = HomeState.Failed
                }
            else {
                _homeState.value = HomeState.Success(movieResponse)
            }

        }
    }


    fun getMovies(title: String) {
        viewModelScope.launch {
            _homeState.value = HomeState.Processing

            val movieResponse = movieRepository.movies(title)

            if (movieResponse.movieList.isEmpty()) {
                _homeState.value = HomeState.Failed
                }
            else {
                _homeState.value = HomeState.Movies(movieResponse.movieList)
            }

        }
    }


}