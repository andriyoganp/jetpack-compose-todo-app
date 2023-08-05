package com.ayeee.data.repository

import com.ayeee.database.dao.TaskDao
import com.ayeee.database.entity.TaskEntity
import com.ayeee.database.entity.TaskType
import com.ayeee.model.ParamCreateOrUpdateTask
import com.ayeee.model.ParamTaskList
import com.ayeee.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject

internal class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
) : TaskRepository {

    override fun getAllTask(): Flow<List<Task>> {
        return taskDao.getTaskEntities().map { list -> list.map { it.toModel() } }
    }
    override fun getTaskByDueDate(query: ParamTaskList): Flow<List<Task>> {
        return taskDao.getTaskEntities(query.dueDate)
            .map { list ->
                list.map { it.toModel() }
            }
    }

    override suspend fun addTask(param: ParamCreateOrUpdateTask) = taskDao.insertOrIgnoreTask(
        TaskEntity(
            id = 0,
            title = param.title,
            description = param.description,
            type = TaskType.fromString(param.type ?: ""),
            dueDate = param.dueDate
        )
    )

    override suspend fun updateTask(param: ParamCreateOrUpdateTask) = taskDao.upsertTask(
        TaskEntity(
            id = 0,
            title = param.title,
            description = param.description,
            type = TaskType.fromString(param.type ?: ""),
            dueDate = param.dueDate,
            updatedAt = Calendar.getInstance().timeInMillis,
        )
    )

    override suspend fun deleteTask(id: Long) = taskDao.deleteTask(id)

    override suspend fun deleteAllTask(): Int = taskDao.deleteAll()
}