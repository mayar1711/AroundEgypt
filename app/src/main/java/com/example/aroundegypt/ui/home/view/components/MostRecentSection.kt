package com.example.aroundegypt.ui.home.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.ui.ViewState


@Composable
fun MostRecentSection(state: State<ViewState<ExperiencesResponse>>) {
    when (val viewState = state.value) {
        is ViewState.Loading -> CircularProgressIndicator()
        is ViewState.Success -> {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Most Recent",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                viewState.data.data.forEach { experience ->
                    ExperienceCard(
                        title = experience.title,
                        image = experience.coverPhoto,
                        views = experience.viewsNo,
                        likes = experience.likesNo,
                        modifier = Modifier.fillMaxWidth(),
                        isMostRecent = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
        is ViewState.Error -> Text("Error: ${viewState.message}")
    }
}