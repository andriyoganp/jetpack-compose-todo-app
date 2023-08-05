package com.ayeee.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ayeee.presentation.screen.AllTaskScreen

const val allTaskRoute = "all_task"

fun NavController.navigateToAllTask(navOptions: NavOptions? = null) {
    this.navigate(allTaskRoute, navOptions)
}

fun NavGraphBuilder.allTask() {
    composable(
        route = allTaskRoute,
    ) {
        AllTaskScreen()
    }
}