package com.kursatkumsuz.noteapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kursatkumsuz.noteapp.model.TaskModel

@Dao
interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task : TaskModel)

    @Query("SELECT * FROM TaskModel")
    fun getAllTask() : LiveData<List<TaskModel>>

    @Query("DELETE FROM TaskModel")
    suspend fun deleteAllTask()
}