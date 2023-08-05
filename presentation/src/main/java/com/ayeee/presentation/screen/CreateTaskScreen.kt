package com.ayeee.presentation.screen

import android.app.DatePickerDialog
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ayeee.presentation.R
import com.ayeee.presentation.uistate.CreateTaskUiState
import com.ayeee.presentation.viewmodel.CreateTaskViewModel
import com.ayeee.ui.annotation.MultiPreviews
import com.ayeee.ui.theme.ToDoListTheme
import com.ayeee.utils.time.getLongFromDate
import com.ayeee.utils.time.setupDatePicker

@Composable
fun CreateTaskScreen(
    viewModel: CreateTaskViewModel = hiltViewModel(),
    onBackPressed: () -> Unit = {}
) {
    val state by viewModel.createTaskState.collectAsStateWithLifecycle()

    // Take out date picker from composable function that used for preview as well
    // there is issue in preview about date picker
    var dueDateDisplay by rememberSaveable { mutableStateOf("") }
    var dueDate: Long? by rememberSaveable { mutableStateOf(null) }
    val datePicker = LocalContext.current.setupDatePicker {
        dueDateDisplay = it
        dueDate = it getLongFromDate "dd-MMMM-yy"
    }

    LaunchedEffect(key1 = state, block = {
        if (state == CreateTaskUiState.Success) {
            onBackPressed()
        }
    })
    CreateTaskScreenContent(
        state,
        datePicker,
        dueDateDisplay,
        onBackPressed
    ) { title, description, type ->
        viewModel.create(title, description, type, dueDate)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateTaskScreenContent(
    state: CreateTaskUiState,
    datePicker: DatePickerDialog? = null,
    dueDateDisplay: String = "",
    onBackPressed: () -> Unit = {},
    onCreateTask: (String, String, String) -> Unit = { _, _, _ -> }
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    if (state is CreateTaskUiState.Error) {
        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
    }
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var type by rememberSaveable { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(title = {
                Text(
                    stringResource(R.string.create_task),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            },
                modifier = Modifier
                    .shadow(1.dp)
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)),
                navigationIcon = {
                    IconButton(onBackPressed) {
                        Icon(Icons.Default.ArrowBack, "back")
                    }
                })
        }
    ) {
        Column(Modifier.padding(it)) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                    .weight(1F),
                verticalArrangement = Arrangement.spacedBy(16.dp)
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
                    Row(
                        modifier = Modifier
                            .border(
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                                shape = MaterialTheme.shapes.extraSmall
                            )
                            .padding(vertical = 16.dp, horizontal = 12.dp)
                            .clickable {
                                datePicker?.show()
                            }
                            .fillMaxWidth(),
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
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 18.dp),
                enabled = state != CreateTaskUiState.Loading,
                onClick = { onCreateTask(title, description, type) },
            ) {
                Text(stringResource(R.string.save).uppercase())
            }
        }
    }
}

@MultiPreviews
@Composable
fun CreateTaskScreenPreview() {
    ToDoListTheme {
        CreateTaskScreenContent(CreateTaskUiState.Idle)
    }
}