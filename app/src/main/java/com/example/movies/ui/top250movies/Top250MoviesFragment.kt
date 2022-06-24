package com.example.movies.ui.top250movies

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.TopMoviesDomainModel
import com.example.movies.databinding.FragmentTop250MoviesBinding
import com.example.movies.ui.base.BaseFragment
import com.example.movies.utils.AppState
import com.example.movies.utils.NetworkObserver
import com.example.movies.utils.ViewModelFactory
import com.example.movies.utils.imageloader.AppImageLoader
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class Top250MoviesFragment : BaseFragment<FragmentTop250MoviesBinding>() {

    @Inject
    lateinit var appImageLoader: AppImageLoader

    @Inject
    lateinit var networkObserver: NetworkObserver

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: Top250MoviesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[Top250MoviesViewModel::class.java]
    }

    private val adapter by lazy {
        Top250MoviesAdapter(appImageLoader)
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as Top250MoviesSubcomponentProvider)
            .initTop250MoviesSubcomponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun getViewBinding(container: ViewGroup?) =
        FragmentTop250MoviesBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topMoviesRecycler.adapter = adapter
        lifecycleScope.launchWhenCreated {
            networkObserver.networkIsAvailable()
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .distinctUntilChanged()
                .collectLatest {
                    viewModel.getTop250Movies(it)
                }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collectLatest {
                    renderData(it)
                }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                refreshAdapter(appState.data as TopMoviesDomainModel)
                binding.progressBar.visibility = View.GONE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(context, appState.error.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun refreshAdapter(models: TopMoviesDomainModel) = adapter.submitList(models.items)
}