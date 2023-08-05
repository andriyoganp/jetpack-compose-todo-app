package com.ayeee.data.repository

import com.ayeee.model.ParamCreateOrUpdateTask
import com.ayeee.model.ParamTaskList
import com.ayeee.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTask(): Flow<List<Task>>

    fun getTaskByDueDate(query: ParamTaskList): Flow<List<Task>>

    suspend fun addTask(param: ParamCreateOrUpdateTask): Long

    suspend fun updateTask(param: ParamCreateOrUpdateTask)

    suspend fun deleteTask(id: Long): Int

    suspend fun deleteAllTask(): Int
}