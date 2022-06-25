package com.example.movies.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UseCaseResponse
import com.example.movies.utils.AppState
import com.example.movies.utils.CHECK_RESPONSE_TAG
import com.example.movies.utils.COROUTINE_EXCEPTION_HANDLER_TAG
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel() {

    protected val mutableStateFlow = MutableStateFlow<AppState>(AppState.Loading)
    val stateFlow = mutableStateFlow.asStateFlow()

    private val coroutineContext =
        SupervisorJob() + Dispatchers.Main.immediate + CoroutineExceptionHandler { _, throwable ->
            Log.e(COROUTINE_EXCEPTION_HANDLER_TAG, throwable.message.toString())
        }
    protected val baseViewModelScope = viewModelScope.plus(coroutineContext)

    protected fun checkResponse(response: UseCaseResponse) {
        when (response) {
            is UseCaseResponse.Error -> {
                mutableStateFlow.value = AppState.Error(response.message)
                Log.e(CHECK_RESPONSE_TAG, response.message)
            }
            is UseCaseResponse.Success<*> -> {
                mutableStateFlow.value = AppState.Success(response.data)
            }
        }
    }
}