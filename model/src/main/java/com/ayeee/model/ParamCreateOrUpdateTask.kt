package com.ayeee.model

data class ParamCreateOrUpdateTask(
    val title: String,
    val description: String,
    val dueDate: Long?,
    val type: String?
)