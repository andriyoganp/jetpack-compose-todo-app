package com.ayeee.model

import java.util.Calendar

private fun tomorrow(): Long {
    return Calendar.getInstance().timeInMillis + 86400000
}

val tasks = listOf(
    Task(
        id = 0L,
        title = "Exercise",
        description = "Exercise in morning for short time",
        type = "Personal",
        dueDate = tomorrow(),
        createdAt = Calendar.getInstance().timeInMillis,
        updatedAt = Calendar.getInstance().timeInMillis + 3600,
    ),
    Task(
        id = 1L,
        title = "Play Game",
        description = "Increase MMR",
        type = "Personal",
        dueDate = tomorrow(),
        createdAt = Calendar.getInstance().timeInMillis,
        updatedAt = Calendar.getInstance().timeInMillis + 3600,
    ),
    Task(
        id = 2L,
        title = "English Course",
        description = "Join zoom meeting for english course",
        type = "Personal",
        dueDate = tomorrow(),
        createdAt = Calendar.getInstance().timeInMillis,
        updatedAt = Calendar.getInstance().timeInMillis + 3600,
    )
)