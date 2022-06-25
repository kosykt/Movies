package com.example.movies.ui.details

import android.util.Log
import com.example.domain.GetDetailsUseCse
import com.example.movies.ui.base.BaseViewModel
import com.example.movies.utils.AppState
import com.example.movies.utils.DETAILS_VIEW_MODEL_TAG
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getDetailsUseCse: GetDetailsUseCse,
    private val detailsSubcomponentProvider: DetailsSubcomponentProvider
) : BaseViewModel() {

    fun getDetails(isNetworkAvailable: Boolean, titleId: String) {
        if (isNetworkAvailable) {
            baseViewModelScope.launch {
                try {
                    val response = getDetailsUseCse.execute(titleId)
                    responseHandler(response)
                } catch (e: Exception) {
                    mutableStateFlow.value = AppState.Error(e.message.toString())
                    Log.e(DETAILS_VIEW_MODEL_TAG, e.message.toString())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        detailsSubcomponentProvider.destroyDetailsSubcomponent()
    }
}