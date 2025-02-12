package com.example.aroundegypt.ui.home.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
fun MostRecentSection(
    experiencesState: State<ViewState<ExperiencesResponse>>,
    navController: NavController
) {
    when (val state = experiencesState.value) {
        is ViewState.Loading -> {
            LoadingView()
        }
        is ViewState.Success -> {
            Text(
                text = stringResource(R.string.most_recent_experiences),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp ) .padding(horizontal = 8.dp)
            )
            if (state.data.experiences.isNotEmpty()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    state.data.experiences.forEach { experience ->
                        ExperienceCard(
                            title = experience.title,
                            image = experience.coverPhoto,
                            views = experience.viewsNo,
                            likes = experience.likesNo,
                            modifier = Modifier.fillMaxWidth(),
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
