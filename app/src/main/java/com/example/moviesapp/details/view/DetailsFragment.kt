package com.example.moviesapp.details.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.FragmentDetailsBinding
import com.example.moviesapp.utils.Constants.Companion.IMAGE_BASE_URL
import com.example.moviesapp.details.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.details.model.MovieDetailsModel

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private val viewModel by viewModels<MovieDetailsViewModel>()
    private var selectedMovieId: Int = 0
    private val safeArgs: DetailsFragmentArgs by navArgs()

    private fun showProgressBar() {
        detailsBinding.detailsProgressBar.visibility = View.VISIBLE
        detailsBinding.detailsView.visibility = View.GONE
    }

    private fun hideProgressBar() {
        detailsBinding.detailsView.visibility = View.VISIBLE
        detailsBinding.detailsProgressBar.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)

        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedMovieId = safeArgs.movieId

        setUpObservers()
        viewModel.fetchMovieDetails(selectedMovieId)
    }

    private fun setUpObservers() {
        viewModel.showLoading.observe(viewLifecycleOwner) {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        }

        viewModel.noMoviesFound.observe(viewLifecycleOwner) {
            Log.e("ERROR_RESPONSE", "No Movies to show")
        }

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            populateMovieData(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateMovieData(moviesResponse: MovieDetailsModel) {
        detailsBinding.tvDuration.text = "${moviesResponse.runtime} minutes"
        detailsBinding.tvOverview.text = moviesResponse.overview
        detailsBinding.tvTitle.text = moviesResponse.original_title
        detailsBinding.tvRating.text =
            "${moviesResponse.vote_average} ( ${moviesResponse.vote_count} responses )"

        Glide.with(requireContext())
            .load("$IMAGE_BASE_URL${moviesResponse.poster_path}")
            .into(detailsBinding.moviePoster)
    }
}