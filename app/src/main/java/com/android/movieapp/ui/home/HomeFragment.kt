package com.android.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.movieapp.Movie
import com.android.movieapp.R
import com.android.movieapp.databinding.FragmentHomeBinding
import com.android.movieapp.hideKeyboard

class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()

    //  Mem-binding
    private var _binding: FragmentHomeBinding? = null

    //  Membuat Getter
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovie.visibility = View.GONE
        binding.tvEmpty.visibility = View.VISIBLE
        binding.pgHome.root.visibility = View.GONE

        binding.btnSearch.setOnClickListener {
            hideKeyboard()
            homeViewModel.getMovies(binding.etTitle.text.toString())
        }

        homeViewModel.homeState().observe(viewLifecycleOwner) { state ->
            when (state) {
                HomeState.Processing -> {
                    binding.rvMovie.visibility = View.GONE
                    binding.tvEmpty.visibility = View.GONE
                    binding.pgHome.root.visibility = View.VISIBLE
                }

                HomeState.Failed -> {
                    binding.rvMovie.visibility = View.GONE
                    binding.tvEmpty.visibility = View.VISIBLE
                    binding.pgHome.root.visibility = View.GONE
                }

                is HomeState.Success -> {
//                    bindData(listOf(state.movieDetail))
                }

                is HomeState.Movies -> {
                    binding.rvMovie.visibility = View.VISIBLE
                    binding.tvEmpty.visibility = View.GONE
                    binding.pgHome.root.visibility = View.GONE

                    bindData(state.movies)
                }
            }

        }
    }


    private fun bindData(movieDetails: List<Movie>) {
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvMovie.layoutManager = layoutManager
        binding.rvMovie.setHasFixedSize(true)

        movieAdapter = MovieAdapter(movieDetails, ::movieClicked)
        binding.rvMovie.adapter = movieAdapter
    }


    private fun movieClicked(movieDetail: Movie) {
        val bundle = bundleOf("title" to movieDetail.title)
        findNavController().navigate(R.id.action_to_movie_detail, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}