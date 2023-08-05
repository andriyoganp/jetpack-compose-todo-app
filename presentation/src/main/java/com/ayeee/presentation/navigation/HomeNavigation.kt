package com.ayeee.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ayeee.presentation.screen.HomeScreen


const val homeRoute = "home"

fun NavGraphBuilder.home() {
    composable(homeRoute) {
        HomeScreen()
    }
}