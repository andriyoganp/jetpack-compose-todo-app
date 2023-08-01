package com.ayeee.database.di

import android.content.Context
import androidx.room.Room
import com.ayeee.database.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "todo-database"

    @Provides
    @Singleton
    fun provideToDoDatabase(
        @ApplicationContext context: Context,
    ): ToDoDatabase =
        Room.databaseBuilder(context, ToDoDatabase::class.java, DATABASE_NAME).build()
}