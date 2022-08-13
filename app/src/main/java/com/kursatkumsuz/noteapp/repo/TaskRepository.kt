package com.kursatkumsuz.noteapp.repo

import androidx.lifecycle.LiveData
import com.kursatkumsuz.noteapp.data.TaskDAO
import com.kursatkumsuz.noteapp.model.TaskModel

class TaskRepository(private val taskDao: TaskDAO) {
    val readTask: LiveData<List<TaskModel>> = taskDao.getAllTask()

    suspend fun addTask(task: TaskModel) {
        taskDao.insert(task)
    }
    suspend fun deleteTask() {
        taskDao.deleteAllTask()
    }
}