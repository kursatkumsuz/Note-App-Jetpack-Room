package com.kursatkumsuz.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel (
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "task")
    val task : String,
    @ColumnInfo(name = "date")
    val date : String,
    @ColumnInfo(name = "color")
    val color : Int,
    @PrimaryKey(autoGenerate = true)
    val primaryKey : Int
    )
