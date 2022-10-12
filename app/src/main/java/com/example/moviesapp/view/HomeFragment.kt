package com.example.moviesapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.utils.OnItemClickListener
import com.example.moviesapp.utils.Resource
import com.example.moviesapp.view.adapter.MovieAdapter
import com.example.moviesapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var adapter: MovieAdapter
    private val viewModel by viewModels<HomeViewModel>()

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
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        viewModel.getPopularMovies()

        viewModel.popularMovies.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { moviesResponse ->
                        adapter.differ.submitList(moviesResponse.results)
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

    private fun setUpRecyclerView() {
        adapter = MovieAdapter(requireContext(), object :
            OnItemClickListener {
            override fun onItemClick(movieId: Int) {
                val bundle = Bundle()
                bundle.putInt("movie_id", movieId)
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.main_frame, DetailsFragment::class.java, bundle)
                    ?.addToBackStack("DetailsFragment")
                    ?.commit()
            }
        })

        homeBinding.rvLatest.layoutManager = GridLayoutManager(context, 2)
        homeBinding.rvLatest.adapter = adapter
    }
}