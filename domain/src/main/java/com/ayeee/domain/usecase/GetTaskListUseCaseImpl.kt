package com.ayeee.domain.usecase

import com.ayeee.model.ParamTaskList
import com.ayeee.data.repository.TaskRepository
import com.ayeee.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetTaskListUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
) : GetTaskListUseCase {
    override fun invoke(param: ParamTaskList): Flow<List<Task>> {
        return if (param.dueDate > 0) {
            repository.getTaskByDueDate(param)
        } else {
            repository.getAllTask()
        }
    }
}