package com.example.aroundegypt.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.data.model.Experience
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.data.model.Meta
import com.example.aroundegypt.data.model.SingleExperienceResponse
import com.example.aroundegypt.data.repo.Repository
import com.example.aroundegypt.ui.ViewState
import com.example.aroundegypt.util.handleFetchError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        fetchData()
    }

    private fun fetchData(){
        if(repository.checkInternetConnection())
            fetchInitialData()
        else
            fetchLocalExperiences()
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
                insertExperiencesInDb(data.experiences)
            }
    }

    private suspend fun fetchRecommendedExperiences() {
        repository.getRecommendedExperiences()
            .catch { handleFetchError(it, _recommendedExperiencesState) }
            .collect { data ->
                _recommendedExperiencesState.value = ViewState.Success(data)
                if (repository.checkInternetConnection()) {
                    insertExperiencesInDb(data.experiences)
                }
        }
    }

    fun searchExperiences(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchResultsState.value = ViewState.Loading
            repository.getSearchedExperiences(searchText)
                .catch { handleFetchError(it, _searchResultsState) }
                .collect { data ->
                    _searchResultsState.value = ViewState.Success(data)
                }
        }
    }

    private suspend fun insertExperiencesInDb(data: List<Experience>) {
        repository.insertAllExperiences(data)
    }

    private  fun fetchLocalExperiences() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllExperiences()
                .catch { handleFetchError(it, _allExperiencesState) }
                .collect { localData ->
                    val mostRecent = localData.filter { it.recommended == 0 }
                    _allExperiencesState.value = ViewState.Success(
                        ExperiencesResponse(experiences = mostRecent, meta = Meta(0, null), pagination = null))
                    val recommended = localData.filter { it.recommended == 1 }
                    _recommendedExperiencesState.value = ViewState.Success(
                        ExperiencesResponse(experiences = recommended, meta = Meta(0, null), pagination = null)
                    )
                }
        }
    }
    fun likeExperience(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postLikeAnExperience(id)
                Log.i("TAG", "likeExperience:${response} ")
            } catch (e: Exception) {
                Log.i("TAG", "likeExperience: ${e.message}")
            }
        }
    }
    private fun deleteAllExperiences() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllExperiences()
        }
    }
}
