package com.Fcih.attendance_admin.Desgin.Fragments.Teachsers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Data.TeacherList.TeacherListViewModel
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.fragment_teacher_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TeacherListFragment : Fragment() {

    lateinit var teacherListViewModel: TeacherListViewModel
    lateinit var teacherList: ArrayList<Teacher>
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

        teacherListViewModel.showingProgressBar()
        teacherListViewModel.hideNoTeacherMessage()

        lifecycleScope.launch(Dispatchers.IO) {
            teacherList = async { teacherListViewModel.getTeachers() }.await()
        }


        var adapter = TeacherAdapter()

        teacherListViewModel.doneRetrieving.observe(viewLifecycleOwner, Observer {
            if (it) {
                adapter.submitList(teacherList)
                teacherListViewModel.doneRetrievingData()
            }
        })
        teacherListViewModel.showProgressbar.observe(viewLifecycleOwner, Observer {
            if (!it) {
                mTeacherListProgressBar.visibility = View.INVISIBLE
            } else {
                mTeacherListProgressBar.visibility = View.VISIBLE
            }
        })

        teacherListViewModel.showNoTeachers.observe(viewLifecycleOwner, Observer {
            if (it) {
                mNoTeachers.visibility = View.VISIBLE
            } else {
                mNoTeachers.visibility = View.INVISIBLE
            }
        })



        adapter.setOnItemClickListener {
//            Navigation.findNavController(this.requireView())
//                .navigate(TeacherListFragmentDirections.)
            Toast.makeText(application, it.teacherName, Toast.LENGTH_SHORT).show()
        }

        rv.adapter = adapter


        var addTeacherTv = view?.findViewById<TextView>(R.id.mAddTeacherTv)
        addTeacherTv?.setOnClickListener {
            Navigation.findNavController(this.requireView())
                .navigate(TeacherListFragmentDirections.actionTeacherListFragmentToAddTeacher())
            teacherListViewModel.doneShowingProgressBar()
        }

        return view
    }
}