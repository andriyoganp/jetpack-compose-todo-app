package com.ayeee.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ayeee.presentation.screen.CreateTaskScreen

const val createTaskRoute = "create_task"

fun NavController.navigateToCreateTask() {
    this.navigate(createTaskRoute) {
        popUpTo("home") {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.createTask(onBackPressed: () -> Unit = {}) {
    composable(
        route = createTaskRoute,
    ) {
        CreateTaskScreen(onBackPressed = onBackPressed)
    }
}