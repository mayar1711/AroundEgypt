package com.example.aroundegypt.ui.experience.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aroundegypt.data.model.SingleExperienceResponse
import com.example.aroundegypt.ui.ViewState
import com.example.aroundegypt.ui.experience.view.components.DetailsSection
import com.example.aroundegypt.ui.experience.view.components.TopImageSection
import com.example.aroundegypt.ui.experience.viewmodel.ExperienceViewModel
import com.example.aroundegypt.ui.home.view.components.ErrorView
import com.example.aroundegypt.ui.home.view.components.LoadingView

@Composable
fun ExperienceScreen(
    id: String,
    viewModel: ExperienceViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getSingleExperience(id)
    }

    val experienceState = viewModel.singleExperienceState.collectAsState()

    when (val state = experienceState.value) {
        is ViewState.Loading -> {
            LoadingView()
        }
        is ViewState.Success -> {
            ExperienceScreenContent(state.data)
        }
        is ViewState.Error -> {
            ErrorView(message = state.message)
        }
    }
}


@Composable
fun ExperienceScreenContent(state: SingleExperienceResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopImageSection(state)
        DetailsSection(state)
    }
}