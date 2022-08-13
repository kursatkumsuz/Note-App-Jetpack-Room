package com.kursatkumsuz.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.kursatkumsuz.noteapp.databinding.TaskRowBinding
import com.kursatkumsuz.noteapp.model.TaskModel

class RecyclerViewAdapter(val taskList: List<TaskModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.TaskHolder>() {
    class TaskHolder(val binding: TaskRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val binding = TaskRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val anim = AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.fade_in)
        holder.itemView.startAnimation(anim)
        holder.binding.titleText.text = taskList[position].title
        holder.binding.taskText.text = taskList[position].task
        holder.binding.dateText.text = taskList[position].date
        holder.binding.cardView.setCardBackgroundColor(taskList[position].color)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}