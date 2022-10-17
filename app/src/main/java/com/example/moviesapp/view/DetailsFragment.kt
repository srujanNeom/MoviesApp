package com.example.moviesapp.view

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
import com.example.moviesapp.model.MovieDetails
import com.example.moviesapp.utils.Constants.Companion.IMAGE_BASE_URL
import com.example.moviesapp.utils.Resource
import com.example.moviesapp.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.navArgs

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
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

        viewModel.getMovieDetails(selectedMovieId)

        viewModel.movieDetails.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { moviesResponse ->
                        populateMovieData(moviesResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("ERROR_RESPONSE", "An Error Occured $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateMovieData(moviesResponse: MovieDetails) {
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