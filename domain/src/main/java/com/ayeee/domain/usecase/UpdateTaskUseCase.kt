package com.ayeee.domain.usecase

import com.ayeee.model.ParamCreateOrUpdateTask

interface UpdateTaskUseCase {
    suspend fun invoke(param: ParamCreateOrUpdateTask)
}