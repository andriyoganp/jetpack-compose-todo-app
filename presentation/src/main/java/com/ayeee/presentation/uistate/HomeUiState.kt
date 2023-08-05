package com.ayeee.presentation.uistate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.ayeee.presentation.navigation.navigateToAllTask
import com.ayeee.presentation.navigation.navigateToOverview

@Stable
class HomeUiState(val navController: NavHostController) {
    private val navOptions
        get() = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }

    fun navigateToOverview() {
        navController.navigateToOverview(navOptions)
    }

    fun navigateToAllTask() {
        navController.navigateToAllTask(navOptions)
    }
}

@Composable
fun rememberHomeUiState(
    navController: NavHostController = rememberNavController(),
): HomeUiState {
    return remember(navController) { HomeUiState(navController) }
}