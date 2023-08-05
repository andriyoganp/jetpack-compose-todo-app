package com.ayeee.todolist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Stable
class ToDoListAppState(val navController: NavHostController) {

    var overrideBackPressed by mutableStateOf(true)
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
}

@Composable
fun rememberToDoListAppState(
    navController: NavHostController = rememberNavController(),
): ToDoListAppState = remember(navController) {
    ToDoListAppState(navController)
}