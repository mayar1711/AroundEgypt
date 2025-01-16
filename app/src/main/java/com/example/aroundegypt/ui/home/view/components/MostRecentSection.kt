package com.example.aroundegypt.ui.home.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.ui.ViewState
import androidx.navigation.NavController


@Composable
fun MostRecentSection(
    experiencesState: State<ViewState<ExperiencesResponse>>,
    navController: NavController
) {
    when (val state = experiencesState.value) {
        is ViewState.Loading -> {
            LoadingView()
        }
        is ViewState.Success -> {
            if (state.data.experiences.isNotEmpty()) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.data.experiences) { experience ->
                        ExperienceCard(
                            title = experience.title,
                            image = experience.coverPhoto,
                            views = experience.viewsNo,
                            likes = experience.likesNo,
                            isMostRecent = true,
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
