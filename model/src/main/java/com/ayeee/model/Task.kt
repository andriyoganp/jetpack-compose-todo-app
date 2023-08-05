package com.ayeee.model

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val type: String,
    val createdAt: Long,
    val updatedAt: Long,
    val dueDate: Long,
)
