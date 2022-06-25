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

    private fun renderData(appState: AppState) {
        when(appState){
            is AppState.Success<*> -> {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
                Toast.makeText(context, "LOADING", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(context, appState.error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}