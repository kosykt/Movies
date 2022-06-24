package com.example.movies.ui.top250movies

import android.util.Log
import com.example.domain.GetTop250MoviesUseCase
import com.example.domain.UseCaseResponse
import com.example.movies.ui.base.BaseViewModel
import com.example.movies.utils.AppState
import com.example.movies.utils.TOP_250_MOVIES_VIEW_MODEL_TAG
import kotlinx.coroutines.launch
import javax.inject.Inject

class Top250MoviesViewModel @Inject constructor(
    private val getTop250MoviesUseCase: GetTop250MoviesUseCase,
    private val top250MoviesSubcomponentProvider: Top250MoviesSubcomponentProvider,
) : BaseViewModel() {

    fun getTop250Movies(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            baseViewModelScope.launch {
                try {
                    val response: UseCaseResponse = getTop250MoviesUseCase.execute()
                    checkResponse(response)
                } catch (e: Exception) {
                    mutableStateFlow.value = AppState.Error(e.message.toString())
                    Log.e(TOP_250_MOVIES_VIEW_MODEL_TAG, e.message.toString())
                }
            }
        }
    }

    private fun checkResponse(response: UseCaseResponse) {
        when (response){
            is UseCaseResponse.Error -> {
                mutableStateFlow.value = AppState.Error(response.message)
            }
            is UseCaseResponse.Success<*> -> {
                mutableStateFlow.value = AppState.Success(response.data)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        top250MoviesSubcomponentProvider.destroyTop250MoviesSubcomponent()
    }
}