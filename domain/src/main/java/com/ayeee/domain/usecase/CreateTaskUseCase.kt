package com.ayeee.domain.usecase

import com.ayeee.model.ParamCreateOrUpdateTask

interface CreateTaskUseCase {
    suspend fun invoke(param: ParamCreateOrUpdateTask): Long
}