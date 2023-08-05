package com.ayeee.todolist.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ayeee.todolist.R
import com.ayeee.ui.annotation.MultiPreviews
import com.ayeee.ui.theme.ToDoListTheme

@Composable
fun SplashScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@MultiPreviews
@Composable
private fun SplashScreenPreview() {
    ToDoListTheme {
        SplashScreen()
    }
}