package com.ayeee.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ayeee.presentation.navigation.createTask
import com.ayeee.presentation.navigation.home
import com.ayeee.presentation.navigation.homeRoute
import com.ayeee.presentation.navigation.parentNavHostController
import com.ayeee.presentation.navigation.taskDetail

@Composable
fun ToDoListNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
) {
    CompositionLocalProvider(parentNavHostController provides navController) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            home()
            createTask {
                navController.popBackStack()
            }
            taskDetail {
                navController.popBackStack()
            }
        }
    }
}