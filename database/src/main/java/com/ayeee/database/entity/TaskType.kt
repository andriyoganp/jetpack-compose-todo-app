package com.ayeee.database.entity

enum class TaskType(
    val value: String,
) {
    PERSONAL(value = "Personal"),
    WORK(value = "Work"),
    OTHER(value = "Other");

    companion object {
        fun fromString(value: String): TaskType {
            return TaskType::class.java.enumConstants?.firstOrNull {
                it.value == value
            } ?: OTHER
        }
    }
}

fun String?.asTaskType() = when (this) {
    null -> TaskType.OTHER
    else -> TaskType.values()
        .firstOrNull { type -> type.value == this } ?: TaskType.OTHER
}