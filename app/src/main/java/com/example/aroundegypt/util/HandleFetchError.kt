package com.example.aroundegypt.util

import android.util.Log
import com.example.aroundegypt.ui.ViewState
import kotlinx.coroutines.flow.MutableStateFlow

 fun <T> handleFetchError(error: Throwable, stateFlow: MutableStateFlow<ViewState<T>>) {
    stateFlow.value = error.localizedMessage?.let {
        ViewState.Error(it)
    } ?: ViewState.Error("An unknown error occurred.")
}