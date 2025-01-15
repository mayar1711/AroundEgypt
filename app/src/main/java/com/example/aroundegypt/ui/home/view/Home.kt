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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.ui.home.view.components.MostRecentSection
import com.example.aroundegypt.ui.home.view.components.RecommendedExperiencesSection
import com.example.aroundegypt.ui.home.view.components.TopBar
import com.example.aroundegypt.ui.home.viewmodel.HomeViewModel

@Composable
fun Home(viewModel: HomeViewModel) {
    val experiencesState = viewModel.experiences.collectAsState()
    val recommendedState = viewModel.recommendedExperiences.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getExperiences()
        viewModel.getRecommendedExperiences()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(viewModel)
        WelcomeSection()
        RecommendedExperiencesSection(recommendedState)
        MostRecentSection(experiencesState)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun WelcomeSection() {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Text(text = "Welcome!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(text = "Now you can explore any experience in 360 degrees and get all the details about it all in one place.", style = MaterialTheme.typography.bodyMedium,  lineHeight = 20.sp)
    }
}
