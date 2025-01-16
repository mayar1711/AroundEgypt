package com.example.aroundegypt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aroundegypt.ui.experience.viewmodel.ExperienceViewModel
import com.example.aroundegypt.ui.home.viewmodel.HomeViewModel
import com.example.aroundegypt.ui.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContent {
                val homeViewModel: HomeViewModel = hiltViewModel()
                val experienceViewModel: ExperienceViewModel = hiltViewModel()
                AppNavigation.NavGraph(
                    homeViewModel = homeViewModel,
                    experienceViewModel = experienceViewModel
                )
            }
        }
    }
}
