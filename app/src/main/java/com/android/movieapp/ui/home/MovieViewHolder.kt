package com.android.movieapp.ui.home

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.movieapp.Movie
import com.android.movieapp.R
import com.android.movieapp.databinding.MovieItemBinding
import com.bumptech.glide.Glide

class MovieViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val binding = MovieItemBinding.bind(itemView)

    fun bindView(movieDetail: Movie, clickListener: (Movie) -> Unit) {

        // Untuk menambah info yang diinginkan dari film
        Glide.with(context)
            .load(movieDetail.poster)
            .placeholder(R.drawable.notfound)
            .into(binding.ivPoster)

        binding.tvTitle.text = movieDetail.title
        binding.tvYear.text = movieDetail.year

        itemView.setOnClickListener {
            clickListener(movieDetail)
        }
    }


}