package com.android.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.movieapp.MovieDetail
import com.android.movieapp.R
import com.android.movieapp.databinding.MovieDetailFragmentBinding
import com.bumptech.glide.Glide

class MovieDetailFragment : Fragment() {

    private var _binding:  MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val receivedTitle = arguments?.getString("title")
        binding.tvTitle.text = receivedTitle

        // Untuk memanggil function dari MovieDetailViewModel.kt
        receivedTitle?.let { title ->
            viewModel.movieByTitle(title)
        }

        viewModel.detailState().observe(viewLifecycleOwner) {state ->
            when(state){
                DetailState.Failed -> {
                    binding.pgDetail.root.visibility = View.GONE }

                DetailState.Processing -> {
                    binding.pgDetail.root.visibility = View.VISIBLE
                    ivPoster.visibility = View.GONE
                    tvTitle.visibility = View.GONE
                    tvYear.visibility = View.GONE
                    tvPlot.visibility = View.GONE }

                is DetailState.Success -> {
                    binding.pgDetail.root.visibility = View.GONE
                    ivPoster.visibility = View.VISIBLE
                    tvTitle.visibility = View.VISIBLE
                    tvYear.visibility = View.VISIBLE
                    tvPlot.visibility = View.VISIBLE

                    bindDetail(state.movieDetail)
                }
            }
        }
    }

    private fun bindDetail(movieDetail: MovieDetail) {
        Glide.with(requireContext())
            .load(movieDetail.poster)
            .placeholder(R.drawable.notfound)
            .into(binding.ivPoster)

        binding.tvTitle.text = movieDetail.title
        binding.tvYear.text = movieDetail.year
        binding.tvPlot.text = movieDetail.plot
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}