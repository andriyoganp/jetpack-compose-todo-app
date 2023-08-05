package com.ayeee.data.di

import com.ayeee.data.repository.TaskRepository
import com.ayeee.data.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    internal abstract fun bindsTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}