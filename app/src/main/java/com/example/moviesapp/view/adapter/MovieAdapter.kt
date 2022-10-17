package com.example.moviesapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.MoviesItemBinding
import com.example.moviesapp.model.MovieInfoModel
import com.example.moviesapp.utils.Constants.Companion.IMAGE_BASE_URL
import com.example.moviesapp.utils.OnItemClickListener

class MovieAdapter(
    private val context: Context,
    private val listener: OnItemClickListener,
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var binding: MoviesItemBinding

    class ViewHolder(viewHolderBinding: MoviesItemBinding) :
        RecyclerView.ViewHolder(viewHolderBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClick(differ.currentList[position].id!!)
        }

        Glide.with(context)
            .load("$IMAGE_BASE_URL${differ.currentList[position].poster_path}")
            .into(binding.moviePoster)

        binding.movieTitle.text = differ.currentList[position].title
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<MovieInfoModel>() {
        override fun areItemsTheSame(oldItem: MovieInfoModel, newItem: MovieInfoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieInfoModel, newItem: MovieInfoModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}