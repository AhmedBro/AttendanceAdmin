package com.Fcih.attendance_admin.Desgin.Fragments.Teachsers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Data.TeacherList.TeacherListViewModel

class TeacherListFragment : Fragment() {

    lateinit var teacherListViewModel: TeacherListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teacher_list, container, false)

        val application = requireNotNull(this.activity).application

        val rv = view.findViewById<RecyclerView>(R.id.mTeacherList)


        teacherListViewModel = ViewModelProvider(this).get(TeacherListViewModel::class.java)

        val manager = GridLayoutManager(activity, 2)

        rv.layoutManager = manager

//        var teachersList = mutableListOf(
//            Teacher("1", "Amr Ghoniem"),
//            Teacher("2", "Mai Eldafrawy"),
//            Teacher("3", "Zlatan Ibrahimovic"),
//            Teacher("4", "Cristiano Ronaldo"),
//            Teacher("1", "حمثلاح"),
//            Teacher("2", "Kilian Mbappe"),
//            Teacher("3", "Lionel Messi"),
//            Teacher("4", "Robert Lewandowski"),
//            Teacher("5", "bla bla5"),
//            Teacher("6", "bla bla6")
//        )
        var adapter = TeacherAdapter()
//        adapter.submitList(teachersList)
        adapter.setOnItemClickListener {
//            Navigation.findNavController(this.requireView())
//                .navigate()
            Toast.makeText(application, it.teacherName, Toast.LENGTH_SHORT).show()
        }

        rv.adapter = adapter


        var addTeacherTv = view?.findViewById<TextView>(R.id.mAddTeacherTv)
        addTeacherTv?.setOnClickListener {
            Navigation.findNavController(this.requireView())
                .navigate(TeacherListFragmentDirections.actionTeacherListFragmentToAddTeacher())
//            Toast.makeText(application, "Clicked", Toast.LENGTH_SHORT).show()
        }

        return view
    }


}