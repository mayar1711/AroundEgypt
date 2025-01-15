package com.example.aroundegypt.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.data.repo.Repository
import com.example.aroundegypt.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _experiences = MutableStateFlow<ViewState<ExperiencesResponse>>(ViewState.Loading)
    val experiences: MutableStateFlow<ViewState<ExperiencesResponse>> = _experiences

    private val _recommendedExperiences = MutableStateFlow<ViewState<ExperiencesResponse>>(ViewState.Loading)
    val recommendedExperiences: MutableStateFlow<ViewState<ExperiencesResponse>> = _recommendedExperiences

    private val _searchedExperiences = MutableStateFlow<ViewState<ExperiencesResponse>>(ViewState.Loading)
    val searchedExperiences: MutableStateFlow<ViewState<ExperiencesResponse>> = _searchedExperiences

    private val _searchText = MutableStateFlow("")
    val searchText: MutableStateFlow<String> get() = _searchText

    fun updateSearchText(text: String) {
        _searchText.value = text
    }

    fun searchExperiences() {
        viewModelScope.launch {
            _searchedExperiences.value = ViewState.Loading
            repository.getSearchedExperiences(_searchText.value)
                .catch { error ->
                    _searchedExperiences.value = error.localizedMessage?.let {
                        ViewState.Error(it)
                    } ?: ViewState.Error("Unknown error")
                }
                .collect {
                    _searchedExperiences.value = ViewState.Success(it)
                }
        }
    }

    fun getExperiences() {
        viewModelScope.launch {
            _experiences.value = ViewState.Loading
            repository.getExperiences()
                .catch { error ->
                    _experiences.value = error.localizedMessage?.let {
                        ViewState.Error(it)
                    } ?: ViewState.Error("Unknown error")
                }
                .collect {
                    _experiences.value = ViewState.Success(it)
                }
        }
    }

    fun getRecommendedExperiences() {
        viewModelScope.launch {
            _recommendedExperiences.value = ViewState.Loading
            repository.getRecommendedExperiences()
                .catch { error ->
                    _recommendedExperiences.value = error.localizedMessage?.let {
                        ViewState.Error(it)
                    } ?: ViewState.Error("Unknown error")
                }
                .collect {
                    _recommendedExperiences.value = ViewState.Success(it)
                }
        }
    }
}
