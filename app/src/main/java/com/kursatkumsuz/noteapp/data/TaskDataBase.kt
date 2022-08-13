package com.kursatkumsuz.noteapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kursatkumsuz.noteapp.model.TaskModel

@Database(entities = [TaskModel::class], version = 4)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDAO

    companion object {
        @Volatile
        private var instance: TaskDataBase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDB(context).also {
                instance = it
            }
        }

        private fun createDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TaskDataBase::class.java,
            "task_database"
        ).build()
    }
}