package com.example.moviesapp.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.databinding.FragmentMoviesListBinding
import com.example.moviesapp.home.adapter.MovieAdapter
import com.example.moviesapp.home.viewmodel.MoviesListViewModel
import com.example.moviesapp.utils.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    private lateinit var homeBinding: FragmentMoviesListBinding

    private lateinit var adapter: MovieAdapter
    private val viewModel: MoviesListViewModel by viewModels()

    private fun showProgressBar() {
        homeBinding.homeProgressBar.visibility = View.VISIBLE
        homeBinding.rvLatest.visibility = View.GONE
    }

    private fun hideProgressBar() {
        homeBinding.homeProgressBar.visibility = View.GONE
        homeBinding.rvLatest.visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        homeBinding = FragmentMoviesListBinding.inflate(inflater, container, false)

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpViewModelStateObservers()
        viewModel.fetchPopularMovies()
    }


    private fun setUpRecyclerView() {
        adapter = MovieAdapter(requireContext(), object :
            OnItemClickListener {
            override fun onItemClick(movieId: Int) {
                val directions = MoviesListFragmentDirections.actionHomeToDetails(movieId = movieId)
                findNavController().navigate(directions)
            }
        })

        homeBinding.rvLatest.layoutManager = GridLayoutManager(context, 2)
        homeBinding.rvLatest.adapter = adapter
    }

    private fun setUpViewModelStateObservers() {
        viewModel.showLoading.observe(viewLifecycleOwner) {
            if (it)
                showProgressBar()
            else
                hideProgressBar()
        }

        viewModel.noMoviesFound.observe(viewLifecycleOwner) {
            Log.e("ERROR_RESPONSE", "No Movies to show")
        }

        viewModel.popularMovies.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it.results)
        }
    }
}