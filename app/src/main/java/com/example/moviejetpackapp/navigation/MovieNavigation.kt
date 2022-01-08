package com.example.moviejetpackapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviejetpackapp.screens.home.DetailsScreen
import com.example.moviejetpackapp.screens.home.HomeScreen

@ExperimentalAnimationApi
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(MovieScreens.HomeScreen.name) {
            //Here we pass where this should lead us to
            HomeScreen(navController)
        }
        composable(
            MovieScreens.DetailScreen.name + "{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                movieId =backStackEntry.arguments?.getString("movie")
            )
        }


    }
}