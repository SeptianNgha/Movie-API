package com.android.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.movieapp.networking.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    private var _detailState = MutableLiveData<DetailState>()
    fun detailState(): LiveData<DetailState> = _detailState

    fun movieByTitle(title: String) {
        viewModelScope.launch {
            _detailState.value = DetailState.Processing

            val movieDetailResponse = movieRepository.movieByTitle(title)
            if (movieDetailResponse.imdbID == "") {
                _detailState.value = DetailState.Failed
                }
            else {
                _detailState.value = DetailState.Success(movieDetailResponse)
            }
        }
    }
}