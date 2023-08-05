package com.ayeee.presentation.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ayeee.model.Task
import com.ayeee.presentation.R
import com.ayeee.presentation.uistate.UpdateTaskUiState
import com.ayeee.presentation.viewmodel.TaskDetailViewModel
import com.ayeee.ui.annotation.MultiPreviews
import com.ayeee.ui.theme.ToDoListTheme
import com.ayeee.utils.time.formattedDate
import com.ayeee.utils.time.getLongFromDate
import com.ayeee.utils.time.setupDatePicker

@Composable
fun TaskDetailScreen(
    task: Task,
    viewModel: TaskDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit = {}
) {
    val updateTaskUiState: UpdateTaskUiState by viewModel.updateTaskState.collectAsStateWithLifecycle()

    // Take out date picker from composable function that used for preview as well
    // there is issue in preview about date picker
    var dueDateDisplay by rememberSaveable {
        mutableStateOf(
            if (task.dueDate > 0) {
                task.dueDate formattedDate "dd-MMMM-yy"
            } else {
                ""
            }
        )
    }
    var dueDate: Long? by rememberSaveable { mutableStateOf(null) }
    val datePicker = LocalContext.current.setupDatePicker {
        dueDateDisplay = it
        dueDate = it getLongFromDate "dd-MMMM-yy"
    }

    LaunchedEffect(key1 = updateTaskUiState, block = {
        if (updateTaskUiState == UpdateTaskUiState.Success) {
            onBackPressed()
        }
    })

    TaskDetailScreenContent(
        task = task,
        datePicker = datePicker,
        dueDateDisplay = dueDateDisplay,
        updateTaskUiState = updateTaskUiState,
        onUpdateTask = { title, description, type ->
            viewModel.update(title, description, type, dueDate)
        },
        onDeleteTask = { id ->
            viewModel.delete(id)
            onBackPressed()
        },
        onBackNavigate = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskDetailScreenContent(
    task: Task,
    updateTaskUiState: UpdateTaskUiState = UpdateTaskUiState.Idle,
    datePicker: DatePickerDialog? = null,
    dueDateDisplay: String = "",
    onUpdateTask: (String, String, String) -> Unit = { _, _, _ -> },
    onDeleteTask: (Long) -> Unit = { _ -> },
    onBackNavigate: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    var title by rememberSaveable { mutableStateOf(task.title) }
    var description by rememberSaveable { mutableStateOf(task.description) }
    var type by rememberSaveable { mutableStateOf(task.type) }

    var expanded by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(R.string.task_detail),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
        },
            modifier = Modifier
                .shadow(1.dp)
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)),
            navigationIcon = {
                IconButton(onBackNavigate) {
                    Icon(Icons.Default.ArrowBack, "back")
                }
            })
    }) {
        Column(Modifier.padding(it)) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                    .weight(1F),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item(1) {
                    OutlinedTextField(
                        value = title,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { newText ->
                            title = newText
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        label = {
                            Text(stringResource(R.string.title))
                        }
                    )
                }
                item(2) {
                    OutlinedTextField(
                        value = description,
                        maxLines = 3,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { newText ->
                            description = newText
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        label = {
                            Text(stringResource(R.string.description))
                        }
                    )
                }
                item(3) {
                    Row(
                        modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    MaterialTheme.colorScheme.outline
                                ), shape = MaterialTheme.shapes.extraSmall
                            )
                            .padding(vertical = 16.dp, horizontal = 12.dp)
                            .fillMaxWidth()
                            .clickable {
                                expanded = true
                            },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = type.ifEmpty {
                                stringResource(R.string.select_type_task)
                            },
                            modifier = Modifier.weight(1F)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box {
                            Icon(
                                Icons.Filled.KeyboardArrowDown,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                            DropdownMenu(
                                modifier = Modifier
                                    .padding(horizontal = 14.dp),
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                }) {
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.personal)) },
                                    onClick = {
                                        type = "Personal"
                                        expanded = false
                                    })
                                Divider()
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.work)) },
                                    onClick = {
                                        type = "Work"
                                        expanded = false
                                    })
                                Divider()
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.other)) },
                                    onClick = {
                                        type = "Other"
                                        expanded = false
                                    })
                            }
                        }
                    }
                }
                item(4) {
                    Box(
                        modifier = Modifier
                            .border(
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                                shape = MaterialTheme.shapes.extraSmall
                            )
                            .padding(vertical = 8.dp, horizontal = 12.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .clickable {
                                    datePicker?.show()
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Filled.DateRange,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = dueDateDisplay.ifEmpty {
                                    stringResource(R.string.select_due_date_optional)
                                }
                            )
                        }
                    }
                }
            }
            Button(
                { onUpdateTask(title, description, type) },
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                updateTaskUiState != UpdateTaskUiState.Loading
            ) {
                Text(stringResource(R.string.update).uppercase())
            }
            TextButton({ onDeleteTask(task.id) },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 18.dp),
                updateTaskUiState != UpdateTaskUiState.Loading,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Gray
                )
            ) {
                Text("Delete this task")
            }
        }
    }
}

@MultiPreviews
@Composable
private fun TaskDetailPreview() {
    ToDoListTheme {
        TaskDetailScreenContent(
            Task(0, "Title", "Description", "", 0L, 0L, 0L)
        )
    }
}