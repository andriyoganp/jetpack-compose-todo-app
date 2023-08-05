package com.ayeee.presentation.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val parentNavHostController =
    compositionLocalOf<NavHostController> { error("parent NavHostController error") }
