package com.ayeee.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayeee.database.converters.TaskTypeConverter
import com.ayeee.database.dao.TaskDao
import com.ayeee.database.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    TaskTypeConverter::class
)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}