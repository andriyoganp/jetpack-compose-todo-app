package com.ayeee.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ayeee.model.Task
import com.ayeee.model.tasks
import com.ayeee.presentation.R
import com.ayeee.presentation.element.TaskItem
import com.ayeee.presentation.uistate.OverviewUiState
import com.ayeee.presentation.viewmodel.OverviewViewModel
import com.ayeee.ui.annotation.MultiPreviews
import com.ayeee.ui.theme.ToDoListTheme

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
) {
    val todayTaskUiState by viewModel.todayUiState.collectAsStateWithLifecycle()
    val tomorrowTaskUiState by viewModel.tomorrowUiState.collectAsStateWithLifecycle()

    OverviewScreenContent(todayTaskUiState, tomorrowTaskUiState, modifier)
}

@Composable
fun OverviewScreenContent(
    todayTaskUiState: OverviewUiState,
    tomorrowTaskUiState: OverviewUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(top = 24.dp)
    ) {
        Text(
            stringResource(id = R.string.app_name), Modifier
                .padding(horizontal = 24.dp), MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.SemiBold,
            )
        )
        Spacer(Modifier.height(24.dp))
        LazyColumn(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
                    shape = MaterialTheme.shapes.extraLarge.copy(
                        bottomEnd = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp)
                    )
                )
                .fillMaxWidth()
                .padding(top = 24.dp)
                .weight(1F), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (todayTaskUiState) {
                is OverviewUiState.Success -> {
                    item("Today") {
                        TaskSection(
                            sectionName = "Today", tasks = todayTaskUiState.tasks,
                        )
                    }
                }

                is OverviewUiState.Error -> {
                    item("Today Error") {
                        Text("Error")
                    }
                }

                OverviewUiState.Loading -> {
                    item("Today Loading") {
                        Text("Loading")
                    }
                }
            }
            when (tomorrowTaskUiState) {
                is OverviewUiState.Success -> {
                    item("Tomorrow") {
                        TaskSection(
                            sectionName = "Tomorrow",
                            tasks = tomorrowTaskUiState.tasks,
                        )
                    }
                }

                is OverviewUiState.Error -> {
                    item("Tomorrow Error") {
                        Text("Error")
                    }
                }

                OverviewUiState.Loading -> {
                    item("Tomorrow Loading") {
                        Text("Loading")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TaskSection(sectionName: String, tasks: List<Task>) {
    var listVisible by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        listVisible = true
    }
    val density = LocalDensity.current
    Column {
        Text(
            sectionName, Modifier.padding(horizontal = 24.dp),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        if (tasks.isNotEmpty()) {
            AnimatedVisibility(
                listVisible,
                enter = fadeIn(
                    animationSpec = tween(delayMillis = 300),
                    initialAlpha = 0.3f
                ),
            ) {
                LazyRow(
                    Modifier
                        .padding(top = 8.dp)
                        .animateEnterExit(
                            enter = slideInHorizontally(
                                animationSpec = tween(delayMillis = 300)
                            ) {
                                with(density) { 20.dp.roundToPx() }
                            },
                        ),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp),
                ) {
                    items(tasks.size, key = { it }) {
                        TaskItem(tasks[it], Modifier.width(160.dp))
                    }
                }
            }
        } else {
            Box(
                Modifier
                    .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .size(160.dp),
            ) {
                Text(
                    "Task of $sectionName is empty",
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@MultiPreviews
@Composable
private fun OverviewScreenPreview() {
    ToDoListTheme {
        OverviewScreenContent(OverviewUiState.Success(tasks), OverviewUiState.Success(tasks))
    }
}