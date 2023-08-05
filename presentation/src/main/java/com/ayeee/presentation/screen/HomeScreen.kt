package com.ayeee.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ayeee.model.tasks
import com.ayeee.presentation.R
import com.ayeee.presentation.navigation.allTask
import com.ayeee.presentation.navigation.navigateToCreateTask
import com.ayeee.presentation.navigation.overview
import com.ayeee.presentation.navigation.overviewRoute
import com.ayeee.presentation.navigation.parentNavHostController
import com.ayeee.presentation.uistate.HomeUiState
import com.ayeee.presentation.uistate.OverviewUiState
import com.ayeee.presentation.uistate.rememberHomeUiState
import com.ayeee.ui.annotation.MultiPreviews
import com.ayeee.ui.theme.ToDoListTheme

@Composable
fun HomeScreen(
    state: HomeUiState = rememberHomeUiState(),
) {
    var selectedIndex by rememberSaveable{ mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            HomeBottomBar(selectedIndex, onBottomItemPressed = {
                when (it) {
                    0 -> {
                        selectedIndex = 0
                        state.navigateToOverview()
                    }
                    1 -> {
                        selectedIndex = 1
                        state.navigateToAllTask()
                    }
                }
            })
        },
        floatingActionButton = { HomeFloatingActionButton() })
    {
        HomeNavHost(state.navController, Modifier.padding(it))
    }
}

@Composable
private fun HomeBottomBar(selectedIndex: Int, onBottomItemPressed: (Int) -> Unit = {}) {
    BottomAppBar(Modifier.height(60.dp)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onBottomItemPressed(0)
                }, colors = IconButtonDefaults.iconButtonColors(
                    contentColor = if (selectedIndex == 0) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
            ) {
                Icon(Icons.Filled.Home, "Overview")
            }
            IconButton(
                onClick = { onBottomItemPressed(1) },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = if (selectedIndex == 1) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
            ) {
                Icon(Icons.Filled.List, "All Task")
            }
        }
    }
}

@Composable
fun HomeFloatingActionButton() {
    val parentNavController = parentNavHostController.current
    ExtendedFloatingActionButton({
        Text(
            stringResource(R.string.create_task),
            style = MaterialTheme.typography.labelLarge,
        )
    }, {
        Icon(Icons.Filled.Add, stringResource(R.string.create_task))
    }, {
        parentNavController.navigateToCreateTask()
    }, expanded = true
    )
}

@Composable
private fun HomeNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navHostController, overviewRoute, modifier) {
        overview()
        allTask()
    }
}

@MultiPreviews
@Composable
fun HomeScreenPreview() {
    val selectedIndex = 0
    ToDoListTheme {
        Scaffold(
            bottomBar = {
                HomeBottomBar(selectedIndex)
            },
            floatingActionButton = { HomeFloatingActionButton() })
        {
            Box(Modifier.padding(it)) {
                OverviewScreenContent(
                    OverviewUiState.Success(tasks),
                    OverviewUiState.Success(tasks)
                )
            }
        }
    }
}