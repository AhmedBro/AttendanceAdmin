package com.Fcih.attendance_admin.Desgin.Fragments.Courses

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.Data.CourseList.CourseListViewModel
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Desgin.Activities.MainActivity
import com.Fcih.attendance_admin.Desgin.Fragments.Teachsers.TeacherListFragment
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore
import com.squareup.okhttp.Dispatcher
import kotlinx.android.synthetic.main.fragment_course_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CourseListFragment : Fragment() {
    lateinit var courseListViewModel: CourseListViewModel
    lateinit var coursesList: ArrayList<Course>
    lateinit var adapter: CourseListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_course_list, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.mCourseList)


        rv.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        courseListViewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)
        courseListViewModel.showProgressBar()
        courseListViewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
        courseListViewModel.showProgressbar.observe(viewLifecycleOwner, Observer {
            if (it) {
                mCoursesProgressBar.visibility = View.VISIBLE
            } else {
                mCoursesProgressBar.visibility = View.GONE

            }
        })


        courseListViewModel.doneRetrieving.observe(viewLifecycleOwner, Observer {
            if (it) {
                if (coursesList.size == 0) {
                    mNoCoursesTV.visibility = View.VISIBLE
                } else {
                    mNoCoursesTV.visibility = View.GONE
                }
                adapter = CourseListAdapter(coursesList)
                courseListViewModel.doneRetrievingdata()
                rv.adapter = adapter
            }
            adapter.setOnItemLongClickListener {
                edit(it)
            }
        })


        var addCourseTv = view?.findViewById<TextView>(R.id.mAddCourseTv)
        addCourseTv?.setOnClickListener {
            Navigation.findNavController(this.requireView())
                .navigate(CourseListFragmentDirections.actionCourseListFragmentToAddCourseFragment())

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    fun edit(course: Course) {

        val bundle: Bundle = Bundle()
        bundle.putSerializable("DataCourse", course)
        val builder1: AlertDialog.Builder = AlertDialog.Builder(MainActivity.context)
        builder1.setMessage(course.courseName)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Delete",
            DialogInterface.OnClickListener {

                    dialog, id ->
                courseListViewModel.deleteCourse(course)
                getData()

                dialog.cancel()

            })

        builder1.setNegativeButton(
            "Edit",
            DialogInterface.OnClickListener {

                    dialog, id ->
                findNavController().navigate(
                    CourseListFragmentDirections.actionCourseListFragmentToEditCourseFragment(course)
                )

            })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

    fun getData() {
        lifecycleScope.launch(Dispatchers.IO) {
            coursesList = async { courseListViewModel.getAllCourses() }.await()
        }
    }
}