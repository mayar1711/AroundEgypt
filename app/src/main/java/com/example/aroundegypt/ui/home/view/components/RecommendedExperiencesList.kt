package com.example.aroundegypt.ui.home.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
fun RecommendedExperiencesSection(state: State<ViewState<ExperiencesResponse>>) {
    when (val viewState = state.value) {
        is ViewState.Loading -> CircularProgressIndicator()
        is ViewState.Success -> {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Recommended Experiences",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(start = 0.dp, end = 0.dp)
                ) {
                    items(viewState.data.data) { experience ->
                        ExperienceCard(
                            title = experience.title,
                            image = experience.coverPhoto,
                            views = experience.viewsNo,
                            likes = experience.likesNo,
                            recommended = true
                        )
                    }
                }
            }
        }
        is ViewState.Error -> Text("Error: ${viewState.message}")
    }
}
