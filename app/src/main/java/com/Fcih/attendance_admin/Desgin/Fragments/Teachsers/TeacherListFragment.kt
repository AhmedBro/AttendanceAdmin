package com.Fcih.attendance_admin.Desgin.Fragments.Teachsers

import android.app.AlertDialog
import android.content.DialogInterface
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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Data.TeacherList.TeacherListViewModel
import com.Fcih.attendance_admin.Desgin.Activities.MainActivity
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.fragment_teacher_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TeacherListFragment : Fragment() {
private lateinit var mynav :NavController

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

            Navigation.findNavController(this.requireView())
                .navigate(TeacherListFragmentDirections.actionTeacherListFragmentToShowTeacherTableFragment(it))
        }

        rv.adapter = adapter


        var addTeacherTv = view?.findViewById<TextView>(R.id.mAddTeacherTv)
        addTeacherTv?.setOnClickListener {
            Navigation.findNavController(this.requireView())
                .navigate(TeacherListFragmentDirections.actionTeacherListFragmentToAddTeacher())
            teacherListViewModel.doneShowingProgressBar()
        }


        adapter.setOnItemLongClickListener {
            deleteTeacher(it)

        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mynav=findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()


    }

    companion object {
        lateinit var teacherListViewModel: TeacherListViewModel
    }

    fun deleteTeacher(teacher: Teacher) {


        val builder1: AlertDialog.Builder = AlertDialog.Builder(MainActivity.context)
        builder1.setMessage("Delete this Doctor?!")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Delete",
            DialogInterface.OnClickListener {

                    dialog, id ->

                teacherListViewModel.DeleteDoc(teacher)
                getData()

                dialog.cancel()

            })

        builder1.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

    fun getData() {
        lifecycleScope.launch(Dispatchers.IO) {
            teacherList = async { teacherListViewModel.getTeachers() }.await()
        }
    }


}