package com.example.movies.ui.top250movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TopMoviesDomainModel
import com.example.movies.databinding.ItemTop250MoviesBinding
import com.example.movies.utils.imageloader.AppImageLoader

class Top250MoviesAdapter(
    private val appImageLoader: AppImageLoader,
) : ListAdapter<TopMoviesDomainModel, Top250MoviesAdapter.Top250MoviesViewHolder>(UsersCallback) {

    inner class Top250MoviesViewHolder(private val vb: ItemTop250MoviesBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun show(model: TopMoviesDomainModel) {
            vb.movieTitle.text = model.title
            vb.movieRating.text = model.imDbRating
            appImageLoader.loadInto(model.image, vb.moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Top250MoviesViewHolder {
        return Top250MoviesViewHolder(
            ItemTop250MoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Top250MoviesViewHolder, position: Int) {
        holder.show(currentList[position])
    }

    companion object UsersCallback : DiffUtil.ItemCallback<TopMoviesDomainModel>() {
        override fun areItemsTheSame(
            oldItem: TopMoviesDomainModel,
            newItem: TopMoviesDomainModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TopMoviesDomainModel,
            newItem: TopMoviesDomainModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}