package com.ayeee.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ayeee.presentation.navigation.parentNavHostController

@Composable
fun ToDoListNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = "",
) {
    CompositionLocalProvider(parentNavHostController provides navController) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {

        }
    }
}