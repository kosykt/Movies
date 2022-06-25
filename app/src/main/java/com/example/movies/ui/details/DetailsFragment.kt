package com.example.movies.ui.details

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.domain.model.TitleDomainModel
import com.example.movies.databinding.FragmentDetailsBinding
import com.example.movies.ui.base.BaseFragment
import com.example.movies.utils.AppState
import com.example.movies.utils.NetworkObserver
import com.example.movies.utils.ViewModelFactory
import com.example.movies.utils.imageloader.AppImageLoader
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    @Inject
    lateinit var appImageLoader: AppImageLoader

    @Inject
    lateinit var networkObserver: NetworkObserver

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]
    }

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onAttach(context: Context) {
        (requireActivity().application as DetailsSubcomponentProvider)
            .initDetailsSubcomponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun getViewBinding(container: ViewGroup?) =
        FragmentDetailsBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            networkObserver.networkIsAvailable()
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .distinctUntilChanged()
                .collectLatest {
                    viewModel.getDetails(
                        isNetworkAvailable = it,
                        titleId = args.titleId
                    )
                }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
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
                val data = appState.data as TitleDomainModel
                with(binding) {
                    detailsTitle.text = data.fullTitle
                    detailsDirector.text = data.directors
                    appImageLoader.loadInto(data.image, detailsPoster)
                    detailsShortDescriptions.text = data.plot
                    setActors(data.stars.split(","))
                }
                binding.detailsProgressBar.visibility = View.GONE
            }
            is AppState.Loading -> {
                binding.detailsProgressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.detailsProgressBar.visibility = View.GONE
                Toast.makeText(context, appState.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setActors(actors: List<String>) {
        val listActorsPosition = listOf(
            binding.detailsActor1,
            binding.detailsActor2,
            binding.detailsActor3,
            binding.detailsActor4,
        )
        for (i in actors.indices) {
            listActorsPosition[i].text = actors[i]
            listActorsPosition[i].visibility = View.VISIBLE
            if (i == listActorsPosition.size) {
                break
            }
        }
    }
}