package com.example.aroundegypt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aroundegypt.ui.experience.view.ExperienceScreen
import com.example.aroundegypt.ui.experience.viewmodel.ExperienceViewModel
import com.example.aroundegypt.ui.home.view.Home
import com.example.aroundegypt.ui.home.viewmodel.HomeViewModel
import com.example.aroundegypt.ui.theme.AroundEgyptTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AroundEgyptTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Experience(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AroundEgyptTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    Home(viewModel = homeViewModel)
}

@Composable
fun Experience(modifier: Modifier = Modifier) {
    val homeViewModel: ExperienceViewModel = hiltViewModel()
    ExperienceScreen("7351979e-7951-4aad-876f-49d5027438bf",viewModel = homeViewModel)
}
