package com.ayeee.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ayeee.presentation.screen.OverviewScreen

const val overviewRoute = "overview"

fun NavController.navigateToOverview(navOptions: NavOptions? = null) {
    this.navigate(overviewRoute, navOptions)
}

fun NavGraphBuilder.overview() {
    composable(
        route = overviewRoute,
    ) {
        OverviewScreen()
    }
}