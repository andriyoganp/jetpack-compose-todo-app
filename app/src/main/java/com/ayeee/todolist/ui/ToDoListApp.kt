package com.ayeee.todolist.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.ayeee.todolist.R
import com.ayeee.todolist.navigation.ToDoListNavHost
import com.ayeee.ui.theme.ToDoListTheme
import kotlinx.coroutines.delay

@Composable
fun ToDoListApp(appState: ToDoListAppState = rememberToDoListAppState()) {
    // when press button hardware or slide gesture
    val context = LocalContext.current
    LaunchedEffect(key1 = appState.overrideBackPressed) {
        if (!appState.overrideBackPressed) {
            delay(2000)
            appState.overrideBackPressed = true
        }
    }

    // Only show on overview or all task list screen
    if (appState.currentDestination.isTopLevelDestinationInHierarchy("")) {
        BackHandler(appState.overrideBackPressed) {
            appState.overrideBackPressed = false
            Toast.makeText(context, R.string.press_again_to_exit, Toast.LENGTH_SHORT).show()
        }
    }
    ToDoListTheme {
        ToDoListNavHost(appState.navController)
    }
}

@Preview
@Composable
private fun ToDoAppListPreview() {
    ToDoListApp()
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(route: String) =
    this?.hierarchy?.any {
        it.route?.contains(route, true) ?: false
    } ?: false
