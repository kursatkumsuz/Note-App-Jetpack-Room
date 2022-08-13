package com.kursatkumsuz.noteapp.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.kursatkumsuz.noteapp.databinding.FragmentAddTaskBinding
import com.kursatkumsuz.noteapp.model.TaskModel
import com.kursatkumsuz.noteapp.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragment : Fragment() {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskViewModel
    private var color = Color.DKGRAY
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        binding.button.setOnClickListener {
            addTaskToDataBase()
            val action = AddTaskFragmentDirections.actionAddTaskFragmentToTaskFragment()
            Navigation.findNavController(it).navigate(action)
        }

        selectColor()

    }

    private fun selectColor() {
        binding.redBtn.setOnClickListener {
            color = Color.RED
            setToastMessage("Selected Red")
        }
        binding.blueBtn.setOnClickListener {
            color = Color.BLUE
            setToastMessage("Selected Blue")
        }
        binding.greenBtn.setOnClickListener {
            color = Color.GREEN
            setToastMessage("Selected Green")
        }
    }

    private fun addTaskToDataBase() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val date = dateFormat.format(Calendar.getInstance().time)
        val titleText = binding.titleText.text.toString()
        val taskText = binding.taskText.text.toString()
        val task = TaskModel(titleText, taskText, date, color, 0)
        viewModel.addTask(task)
    }

    private fun setToastMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}