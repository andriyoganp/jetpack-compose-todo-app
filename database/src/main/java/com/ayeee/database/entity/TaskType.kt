package com.ayeee.database.entity

enum class TaskType(
    val value: String,
) {
    PERSONAL(value = "Personal"),
    WORK(value = "Work"),
    OTHER(value = "Other"),
    UNKNOWN(value = "Unknown");

    companion object {
        fun fromString(value: String): TaskType {
            return TaskType::class.java.enumConstants?.firstOrNull {
                it.value == value
            } ?: UNKNOWN
        }
    }
}

fun String?.asTaskType() = when (this) {
    null -> TaskType.UNKNOWN
    else -> TaskType.values()
        .firstOrNull { type -> type.value == this } ?: TaskType.UNKNOWN
}