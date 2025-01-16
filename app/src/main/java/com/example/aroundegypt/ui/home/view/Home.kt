package com.example.aroundegypt.ui.home.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.ui.home.view.components.ExperienceList
import com.example.aroundegypt.ui.home.view.components.MostRecentSection
import com.example.aroundegypt.ui.home.view.components.RecommendedExperiencesSection
import com.example.aroundegypt.ui.home.view.components.TopBar
import com.example.aroundegypt.ui.home.viewmodel.HomeViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues


@Composable
fun Home(viewModel: HomeViewModel) {
    val experiencesState = viewModel.allExperiencesState.collectAsState()
    val recommendedState = viewModel.recommendedExperiencesState.collectAsState()
    var searchText by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    val searchResultsState = viewModel.searchResultsState.collectAsState()


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            TopBar(
                searchText = searchText,
                onSearchTextChanged = {
                    searchText = it
                    if (it.isBlank()) {
                        isSearchActive = false
                    } else {
                        isSearchActive = true
                        viewModel.searchExperiences(it)
                    }
                },
                isSearchActive = isSearchActive,
                onSearchSubmitted = {
                    isSearchActive = true
                    viewModel.searchExperiences(searchText)
                }
            )
        }

        if (!isSearchActive) {
            item { WelcomeSection() }
            item { RecommendedExperiencesSection(recommendedState) }
            item { MostRecentSection(experiencesState) }
        } else {
            item { ExperienceList(experiencesState = searchResultsState, isSearchActive = true) }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}


@Composable
fun WelcomeSection() {
    androidx.compose.foundation.layout.Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(text = "Welcome!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(text = "Now you can explore any experience in 360 degrees and get all the details about it all in one place.", style = MaterialTheme.typography.bodyMedium,  lineHeight = 20.sp)
    }
}