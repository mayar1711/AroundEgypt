package com.example.aroundegypt.ui.experience.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.data.model.SingleExperienceResponse
import com.example.aroundegypt.data.repo.Repository
import com.example.aroundegypt.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExperienceViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _singleExperienceState = MutableStateFlow<ViewState<SingleExperienceResponse>>(ViewState.Loading)
    val singleExperienceState = _singleExperienceState

    fun getSingleExperience(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _singleExperienceState.value = ViewState.Loading
            repository.getSingleExperience(id)
                .catch { handleFetchError(it) }
                .collect { data -> _singleExperienceState.value = ViewState.Success(data) }
        }
    }

    private fun handleFetchError(error: Throwable) {
        _singleExperienceState.value = error.localizedMessage?.let {
            ViewState.Error(it)
        } ?: ViewState.Error("An unknown error occurred.")
        Log.e("ExperienceViewModel", "Error fetching single experience: ", error)
    }
}