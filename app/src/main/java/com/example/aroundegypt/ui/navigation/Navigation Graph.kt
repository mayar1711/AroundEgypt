package com.example.aroundegypt.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aroundegypt.ui.experience.view.ExperienceScreen
import com.example.aroundegypt.ui.home.view.Home
import com.example.aroundegypt.ui.home.viewmodel.HomeViewModel
import com.example.aroundegypt.ui.experience.viewmodel.ExperienceViewModel
import com.example.aroundegypt.ui.theme.AroundEgyptTheme
import com.example.aroundegypt.util.Constants.EXPERIENCE_ROUTE
import com.example.aroundegypt.util.Constants.HOME_ROUTE

object AppNavigation {


    val argumentsExperience: List<NamedNavArgument> = listOf(
        navArgument("id") { type = NavType.StringType }
    )

    @Composable
    fun NavGraph(homeViewModel: HomeViewModel, experienceViewModel: ExperienceViewModel) {
        val navController = rememberNavController()
        AroundEgyptTheme {
            NavHost(navController = navController, startDestination = HOME_ROUTE) {
                composable(HOME_ROUTE) {
                    Home(viewModel = homeViewModel, navController = navController)
                }
                composable(EXPERIENCE_ROUTE, arguments = argumentsExperience) { backStackEntry ->
                    ExperienceScreen(id = backStackEntry.arguments?.getString("id").toString(), viewModel = experienceViewModel)
                }
            }
        }
    }
}