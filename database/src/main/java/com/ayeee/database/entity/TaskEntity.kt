package com.ayeee.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayeee.model.Task
import java.util.Calendar

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val type: TaskType,
    @ColumnInfo("created_at")
    val createdAt: Long = Calendar.getInstance().timeInMillis,
    @ColumnInfo("due_date")
    val dueDate: Long? = null,
) {
    fun toModel() = Task(
        id = id,
        title = title,
        description = description,
        type = type.value,
        createdAt = createdAt,
        dueDate = dueDate ?: 0L
    )
}
