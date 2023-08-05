package com.ayeee.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.ayeee.database.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query(
        """
        SELECT * FROM tasks
        ORDER BY due_date DESC, created_at DESC
        """
    )
    fun getTaskEntities(): Flow<List<TaskEntity>>

    @Query(
        """
            SELECT * FROM tasks
            WHERE due_date IS NOT NULL AND due_date > :dueDate - 86400000 AND due_date <= :dueDate
            ORDER BY due_date DESC, created_at DESC
            """
    )
    fun getTaskEntities(dueDate: Long): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTask(task: TaskEntity): Long

    @Upsert
    suspend fun upsertTask(task: TaskEntity)

    @Query("""DELETE FROM tasks WHERE id = :id""")
    suspend fun deleteTask(id: Long): Int

    @Query("""DELETE FROM tasks""")
    suspend fun deleteAll(): Int
}