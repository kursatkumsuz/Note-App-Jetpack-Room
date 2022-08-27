package com.kursatkumsuz.noteapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.noteapp.data.TaskDataBase
import com.kursatkumsuz.noteapp.model.TaskModel
import com.kursatkumsuz.noteapp.repo.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    val taskList : LiveData<List<TaskModel>>
    private val repository : TaskRepository

    init {
        val taskDao = TaskDataBase.invoke(application).taskDao()
        repository = TaskRepository(taskDao)
        taskList = repository.readTask
    }

    fun addTask(task : TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }
    fun deleteTask(task: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
        Toast.makeText(getApplication() , "Deleted Tasks" , Toast.LENGTH_SHORT).show()
    }
}