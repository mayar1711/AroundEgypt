package com.example.aroundegypt.ui.home.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.ui.ViewState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier

@Composable
fun ExperienceList(
    experiencesState: State<ViewState<ExperiencesResponse>>,
    isSearchActive: Boolean
) {
    Column(modifier = Modifier.padding(16.dp)) {
        when (val state = experiencesState.value) {
            is ViewState.Loading -> {
                LoadingView()
            }
            is ViewState.Success -> {
                if(isSearchActive){
                    if(state.data.experiences.isNotEmpty()){
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            state.data.experiences.forEach { experience ->
                                ExperienceCard(
                                    title = experience.title,
                                    image = experience.coverPhoto,
                                    views = experience.viewsNo,
                                    likes = experience.likesNo,
                                    isMostRecent = true,
                                    id = experience.id
                                )
                            }
                        }

                    }else{
                        NoResultView()
                    }
                }
            }
            is ViewState.Error -> {
                ErrorView(message = state.message)
            }

        }
    }
}