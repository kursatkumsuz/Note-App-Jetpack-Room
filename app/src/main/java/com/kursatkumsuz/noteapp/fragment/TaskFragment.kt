package com.kursatkumsuz.noteapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kursatkumsuz.noteapp.adapter.RecyclerViewAdapter
import com.kursatkumsuz.noteapp.databinding.FragmentTaskBinding
import com.kursatkumsuz.noteapp.model.TaskModel
import com.kursatkumsuz.noteapp.viewmodel.TaskViewModel


class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskViewModel
    private var adapter = RecyclerViewAdapter()


    private val itemCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedTask = adapter.taskList[layoutPosition]
            viewModel.deleteTask(selectedTask)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        //Functions
        observeLiveData()
        navigateToAddFragment()
        // RecyclerView
        StaggeredGridLayoutManager(
            3,
            StaggeredGridLayoutManager.VERTICAL
        ).apply {
            binding.recyclerView.layoutManager = this
        }
        binding.recyclerView.adapter = adapter

        ItemTouchHelper(itemCallBack).attachToRecyclerView(binding.recyclerView)
    }

    private fun observeLiveData() {
        viewModel.taskList.observe(viewLifecycleOwner, Observer { task ->
            task?.let {
                adapter.taskList = task
            }
        })
    }

    private fun navigateToAddFragment() {
        binding.createButton.setOnClickListener {
            val action = TaskFragmentDirections.actionTaskFragmentToAddTaskFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}