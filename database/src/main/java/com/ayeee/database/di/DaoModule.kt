package com.ayeee.database.di

import com.ayeee.database.ToDoDatabase
import com.ayeee.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesTaskDao(
        database: ToDoDatabase
    ): TaskDao = database.taskDao()
}