package com.example.aroundegypt.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.data.repo.Repository
import com.example.aroundegypt.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _allExperiencesState = MutableStateFlow<ViewState<ExperiencesResponse>>(ViewState.Loading)
    val allExperiencesState = _allExperiencesState

    private val _recommendedExperiencesState = MutableStateFlow<ViewState<ExperiencesResponse>>(ViewState.Loading)
    val recommendedExperiencesState = _recommendedExperiencesState

    private var cachedExperiences: ExperiencesResponse? = null

    private val _searchResultsState = MutableStateFlow<ViewState<ExperiencesResponse>>(ViewState.Loading)
    val searchResultsState = _searchResultsState


    init {
        fetchInitialData()
    }


    private fun fetchInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            _allExperiencesState.value = ViewState.Loading
            _recommendedExperiencesState.value = ViewState.Loading

            val experiencesDeferred = async { fetchExperiences() }
            val recommendedDeferred = async { fetchRecommendedExperiences() }

            experiencesDeferred.await()
            recommendedDeferred.await()
        }
    }

    private suspend fun fetchExperiences() {
        repository.getExperiences()
            .catch { handleFetchError(it, _allExperiencesState) }
            .collect { data ->
                cachedExperiences = data
                _allExperiencesState.value = ViewState.Success(data)
            }
    }

    private suspend fun fetchRecommendedExperiences() {
        repository.getRecommendedExperiences()
            .catch { handleFetchError(it, _recommendedExperiencesState) }
            .collect { data -> _recommendedExperiencesState.value = ViewState.Success(data) }
    }
    fun searchExperiences(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchResultsState.value = ViewState.Loading
            repository.getSearchedExperiences(searchText)
                .catch { handleFetchError(it, _searchResultsState) }
                .collect { data -> _searchResultsState.value = ViewState.Success(data) }

        }
    }


    private fun <T> handleFetchError(error: Throwable, stateFlow: MutableStateFlow<ViewState<T>>) {
        stateFlow.value = error.localizedMessage?.let {
            ViewState.Error(it)
        } ?: ViewState.Error("An unknown error occurred.")
        Log.e("HomeViewModel", "Error fetching data: ", error)
    }
}