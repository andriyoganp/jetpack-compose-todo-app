package com.ayeee.domain.di

import com.ayeee.domain.usecase.CreateTaskUseCase
import com.ayeee.domain.usecase.CreateTaskUseCaseImpl
import com.ayeee.domain.usecase.DeleteTaskUseCase
import com.ayeee.domain.usecase.DeleteTaskUseCaseImpl
import com.ayeee.domain.usecase.GetTaskListUseCase
import com.ayeee.domain.usecase.GetTaskListUseCaseImpl
import com.ayeee.domain.usecase.UpdateTaskUseCase
import com.ayeee.domain.usecase.UpdateTaskUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    internal abstract fun bindsGetTaskListUseCase(impl: GetTaskListUseCaseImpl): GetTaskListUseCase

    @Binds
    internal abstract fun bindsCreateTaskUseCase(impl: CreateTaskUseCaseImpl): CreateTaskUseCase

    @Binds
    internal abstract fun bindsUpdateTaskUseCase(impl: UpdateTaskUseCaseImpl): UpdateTaskUseCase

    @Binds
    internal abstract fun bindsDeleteTaskUseCase(impl: DeleteTaskUseCaseImpl): DeleteTaskUseCase
}