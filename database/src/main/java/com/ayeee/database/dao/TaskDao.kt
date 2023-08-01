package com.ayeee.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.ayeee.database.entity.TaskEntity
import com.ayeee.database.entity.TaskType
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("""
            SELECT * FROM tasks
            WHERE type = :type
            OR due_date = :dueDate
            ORDER BY due_date DESC
            """)
    fun getTaskEntities(type: TaskType, dueDate: Long) : Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTask(task: TaskEntity)

    @Upsert
    suspend fun upsertTask(task: TaskEntity)

    @Query("""
        DELETE FROM tasks
        WHERE id = :id
    """)
    suspend fun deleteTask(id: Long)
}