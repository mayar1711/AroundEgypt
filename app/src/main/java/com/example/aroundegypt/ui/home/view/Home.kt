package com.example.aroundegypt.ui.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aroundegypt.R
import com.example.aroundegypt.ui.home.view.components.ExperienceList
import com.example.aroundegypt.ui.home.view.components.MostRecentSection
import com.example.aroundegypt.ui.home.view.components.RecommendedExperiencesSection
import com.example.aroundegypt.ui.home.view.components.TopBar
import com.example.aroundegypt.ui.home.viewmodel.HomeViewModel


@Composable
fun Home(viewModel: HomeViewModel, navController: NavController = rememberNavController()) {
    val experiencesState = viewModel.allExperiencesState.collectAsState()
    val recommendedState = viewModel.recommendedExperiencesState.collectAsState()
    var searchText by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    val searchResultsState = viewModel.searchResultsState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
            },
            onBackButtonClicked = {
                searchText = ""
                isSearchActive = false
            },
            onExitSearchClicked = {
                searchText = ""
                isSearchActive = false
            }
        )

        if (!isSearchActive) {
            WelcomeSection()
            RecommendedExperiencesSection(recommendedState, navController = navController)
            Spacer(modifier = Modifier.height(16.dp))
            MostRecentSection(experiencesState, navController = navController)
        } else {
            Text(
                text = stringResource(R.string.Search_Results),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            ExperienceList(experiencesState = searchResultsState, isSearchActive = true)
        }
    }
}

@Composable
fun WelcomeSection() {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = stringResource(R.string.Welcome),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 20.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
