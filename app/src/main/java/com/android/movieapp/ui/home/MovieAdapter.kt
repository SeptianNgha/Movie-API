package com.android.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.movieapp.Movie
import com.android.movieapp.databinding.MovieItemBinding

class MovieAdapter(
    private val movieDetails: List<Movie>,
    private val clickListener:(Movie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding.root, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as MovieViewHolder
        viewHolder.bindView(movieDetails[position], clickListener)
    }

    override fun getItemCount(): Int {
        return movieDetails.size
    }
}