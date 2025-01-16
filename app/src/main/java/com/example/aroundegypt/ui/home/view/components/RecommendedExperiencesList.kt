package com.example.aroundegypt.ui.home.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.ui.ViewState
import androidx.navigation.NavController
import com.example.aroundegypt.R


@Composable
fun RecommendedExperiencesSection(
    recommendedState: State<ViewState<ExperiencesResponse>>,
    navController: NavController
) {
    when (val state = recommendedState.value) {
        is ViewState.Loading -> {
           // LoadingView()
        }
        is ViewState.Success -> {
            Text(
                text = stringResource(R.string.recommended_experiences),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp ) .padding(horizontal = 8.dp)
            )
            if (state.data.experiences.isNotEmpty()) {
                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.data.experiences) { experience ->
                        ExperienceCard(
                            title = experience.title,
                            image = experience.coverPhoto,
                            views = experience.viewsNo,
                            likes = experience.likesNo,
                            recommended = true,
                            id = experience.id,
                            navController = navController
                        )
                    }
                }
            } else {
                NoResultView()
            }
        }
        is ViewState.Error -> {
            ErrorView(message = state.message)
        }
        else -> {}
    }
}
