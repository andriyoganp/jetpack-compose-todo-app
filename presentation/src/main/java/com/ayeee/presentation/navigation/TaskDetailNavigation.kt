package com.ayeee.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ayeee.model.Task
import com.ayeee.presentation.screen.TaskDetailScreen
import com.ayeee.utils.converter.convertToJson
import com.ayeee.utils.converter.fromJson

private const val taskObject = "task"
const val taskDetailRoute = "taskDetail/task={$taskObject}"

fun NavController.navigateToTaskDetail(task: Task, navOptions: NavOptions? = null) {
    this.navigate(taskDetailRoute.replace("{$taskObject}", task.convertToJson()), navOptions)
}

fun NavGraphBuilder.taskDetail(onBackPressed: () -> Unit = {}) {
    composable(
        route = taskDetailRoute,
    ) {
        val taskJson =  it.arguments?.getString(taskObject)
        val task = taskJson.fromJson(Task::class.java)
        TaskDetailScreen(task, onBackPressed = onBackPressed)
    }
}