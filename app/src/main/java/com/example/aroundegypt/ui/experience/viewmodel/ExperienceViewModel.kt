package com.example.aroundegypt.ui.experience.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.data.model.SingleExperienceResponse
import com.example.aroundegypt.data.repo.Repository
import com.example.aroundegypt.ui.ViewState
import com.example.aroundegypt.util.handleFetchError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ExperienceViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _singleExperienceState = MutableStateFlow<ViewState<SingleExperienceResponse>>(ViewState.Loading)
    val singleExperienceState = _singleExperienceState

    fun getSingleExperience(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _singleExperienceState.value = ViewState.Loading
            repository.getSingleExperience(id)
                .catch { handleFetchError(it , _singleExperienceState) }
                .collect { data -> _singleExperienceState.value = ViewState.Success(data) }
        }
    }
    fun likeExperience(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postLikeAnExperience(id)
                getSingleExperience(id)
                Log.i("TAG", "likeExperience:${response} ")
            } catch (e: Exception) {
                Log.i("TAG", "likeExperience: ${e.message}")
            }
        }
    }
    fun checkNetworkConnection(): Boolean {
        return repository.checkInternetConnection()

    }
}