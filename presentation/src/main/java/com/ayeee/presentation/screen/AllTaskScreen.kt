package com.ayeee.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ayeee.model.tasks
import com.ayeee.presentation.R
import com.ayeee.presentation.element.TaskItem
import com.ayeee.presentation.uistate.AllTaskUiState
import com.ayeee.presentation.viewmodel.AllTaskScreenViewModel
import com.ayeee.ui.annotation.MultiPreviews
import com.ayeee.ui.theme.ToDoListTheme

@Composable
fun AllTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: AllTaskScreenViewModel = hiltViewModel()
) {
    val allTaskUiState by viewModel.allTaskUiState.collectAsStateWithLifecycle()
    AllTaskScreenContent(allTaskUiState, modifier, onDeleteAllTask = {
        viewModel.deleteAll()
    })
}

@Composable
private fun AllTaskScreenContent(
    allTaskUiState: AllTaskUiState,
    modifier: Modifier = Modifier,
    onDeleteAllTask: () -> Unit = {}
) {
    var confirmationDialog by remember { mutableStateOf(false) }
    Column(
        modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        if (confirmationDialog) {
            DeleteConfirmationDialog(onCloseDialog = {
                confirmationDialog = false
            }) {
                confirmationDialog = false
                onDeleteAllTask()
            }
        }
        Spacer(Modifier.height(24.dp))
        Row {
            Text(
                stringResource(id = R.string.all_task),
                Modifier
                    .padding(horizontal = 24.dp)
                    .weight(1F),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )
            if (allTaskUiState is AllTaskUiState.Success && allTaskUiState.tasks.isNotEmpty()) {
                IconButton(
                    onClick = { confirmationDialog = true }, modifier = Modifier
                        .padding(horizontal = 24.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(Icons.Filled.Delete, "")
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
                    shape = MaterialTheme.shapes.extraLarge.copy(
                        bottomEnd = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp)
                    )
                )
                .fillMaxWidth()
        ) {
            LazyVerticalGrid(
                GridCells.Fixed(2),
                Modifier
                    .weight(1F),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(24.dp)
            ) {
                when (allTaskUiState) {
                    is AllTaskUiState.Success -> {
                        items(allTaskUiState.tasks.size) {
                            TaskItem(
                                task = allTaskUiState.tasks[it],
                            )
                        }
                    }

                    is AllTaskUiState.Error -> {
                        item("error", span = {
                            GridItemSpan(2)
                        }) {
                            Text("Error")
                        }
                    }

                    AllTaskUiState.Loading -> {
                        item("loading", span = {
                            GridItemSpan(2)
                        }) {
                            Text("Loading")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(onCloseDialog: () -> Unit, onConfirmDialog: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCloseDialog,
        title = {
            Text(
                text = "Are you sure want to delete all task?",
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text("All task will delete permanently")
        },
        confirmButton = {
            Button(
                onClick = onCloseDialog, colors =
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                )
            ) {
                Text("Cancel")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onConfirmDialog,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Gray
                )
            ) {
                Text("Delete All")
            }
        }
    )
}

@MultiPreviews
@Composable
private fun AllTaskScreenPreview() {
    ToDoListTheme {
        AllTaskScreenContent(AllTaskUiState.Success(tasks))
    }
}