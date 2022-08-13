package com.kursatkumsuz.noteapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kursatkumsuz.noteapp.adapter.RecyclerViewAdapter
import com.kursatkumsuz.noteapp.databinding.FragmentTaskBinding
import com.kursatkumsuz.noteapp.model.TaskModel
import com.kursatkumsuz.noteapp.viewmodel.TaskViewModel


class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var list: List<TaskModel>
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

        StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        ).apply {
            binding.recyclerView.layoutManager = this
        }

        binding.createButton.setOnClickListener {
            val action = TaskFragmentDirections.actionTaskFragmentToAddTaskFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteTask()
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.taskList.observe(viewLifecycleOwner, Observer { task ->
            task?.let {
                list = task
                adapter = RecyclerViewAdapter(task)
                binding.recyclerView.adapter = adapter
            }
        })
    }


}