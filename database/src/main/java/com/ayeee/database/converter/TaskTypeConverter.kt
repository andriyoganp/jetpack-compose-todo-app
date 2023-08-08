package com.ayeee.database.converter

import androidx.room.TypeConverter
import com.ayeee.database.entity.TaskType
import com.ayeee.database.entity.asTaskType

class TaskTypeConverter {
    @TypeConverter
    fun taskTypeToString(value: TaskType?): String? =
        value?.let(TaskType::value)

    @TypeConverter
    fun stringToTaskType(value: String?): TaskType = value.asTaskType()
}