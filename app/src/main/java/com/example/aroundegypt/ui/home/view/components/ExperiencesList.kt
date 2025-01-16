package com.example.aroundegypt.ui.home.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.ui.ViewState

@Composable
fun ExperienceList(
    experiencesState: State<ViewState<ExperiencesResponse>>,
    isSearchActive: Boolean
) {
    Column {
        when (val state = experiencesState.value) {
            is ViewState.Loading -> {
                LoadingView()
            }

            is ViewState.Success -> {
                if(isSearchActive){
                    if(state.data.data.isNotEmpty()){
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(state.data.data) { experience ->
                                ExperienceCard(
                                    title = experience.title,
                                    image = experience.coverPhoto,
                                    views = experience.viewsNo,
                                    likes = experience.likesNo,
                                    isMostRecent = true
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

            else -> {}

        }

    }

}