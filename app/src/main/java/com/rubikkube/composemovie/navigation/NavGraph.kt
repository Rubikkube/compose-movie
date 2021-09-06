package com.rubikkube.composemovie.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.rubikkube.composemovie.screens.details.DetailsScreen
import com.rubikkube.composemovie.screens.home.HomeScreen
import com.rubikkube.composemovie.viewmodel.AppViewModel

@Preview(showBackground = true)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) {
        Actions(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            val viewModel: AppViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it),
            )
            HomeScreen(viewModel, actions.actionDetails)
        }
        composable(
            "${Routes.Details.route}/{movieId}",
            arguments = listOf( navArgument("movieId") { type = NavType.StringType } )
        ) {
            val viewModel = hiltViewModel<AppViewModel>()
            DetailsScreen(
                viewModel, actions.back,
                it.arguments?.getString("movieId") ?: ""
            )
        }
    }
}

class Actions(navController: NavHostController) {
    var back: () -> Unit = {
        navController.navigateUp()
    }
    var actionDetails: (String) -> Unit = { movieId ->
        navController.navigate("${Routes.Details.route}/$movieId")
    }
}