package com.Fcih.attendance_admin.Desgin.Fragments.Teachsers

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.Data.CourseList.*
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Desgin.Activities.MainActivity
import com.Fcih.attendance_admin.Desgin.Fragments.Courses.CourseCheckBoxAdapter
import com.Fcih.attendance_admin.Desgin.Fragments.Courses.CourseListAdapter
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.fragment_show_teacher_table.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ShowTeacherTableFragment : Fragment() {
    lateinit var mTeacher: Teacher
    lateinit var showtableviewmodel: ShowTeacherTableViewModel
    lateinit var coursesList: ArrayList<Course>

    lateinit var course_checkbox_adapter: CourseCheckBoxAdapter
    lateinit var adapter: CourseListAdapter

    var coursecodes: ArrayList<String> = ArrayList()
    var clickedCourse = Course()


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_show_teacher_table, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.mTableRecyclerView)


        rv.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        showtableviewmodel = ViewModelProvider(this).get(ShowTeacherTableViewModel::class.java)



        showtableviewmodel.showProgressBar()
        showtableviewmodel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        showtableviewmodel.showProgressbar.observe(viewLifecycleOwner, Observer {
            if (it) {
                mTeacherTableProgressBar.visibility = View.VISIBLE
            } else {
                mTeacherTableProgressBar.visibility = View.GONE

            }
        })


        showtableviewmodel.doneRetrieving.observe(viewLifecycleOwner, Observer {
            if (it) {
                if (coursesList.size == 0) {
                    mTeacherTableNoCoursesTV.visibility = View.VISIBLE
                    mTeacherTableProgressBar.visibility = View.GONE
                } else {
                    mTeacherTableNoCoursesTV.visibility = View.GONE
                }
                adapter = CourseListAdapter(coursesList)
                showtableviewmodel.doneRetrievingdata()
                rv.adapter = adapter
            }

            adapter.setOnItemLongClickListener {
                clickedCourse = it
                deleteCourse(it)
            }

        })

        showtableviewmodel.doneDeleting.observe(viewLifecycleOwner, Observer {
            if (it) {
                coursecodes.remove(clickedCourse.courseCode + clickedCourse.courseGroup)
                getData()
                showtableviewmodel.doneDeletingCourse()
            }
        })
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var linearLayoutManager = LinearLayoutManager(this.context)
        mTeacher = ShowTeacherTableFragmentArgs.fromBundle(requireArguments()).teacherData



        if (mTeacher.CoursesId == null) {
            coursecodes = ArrayList()


        } else {
            coursecodes = mTeacher.CoursesId!!
        }


        mTeacher_Name.text = mTeacher.teacherName+mTeacher.id

        getData()

        mAddTeacherCourseTv.setOnClickListener {
            Navigation.findNavController(this.requireView())
                .navigate(
                    ShowTeacherTableFragmentDirections.actionShowTeacherTableFragmentToAddCourseToTeacherFragment(
                        mTeacher
                    )
                )
        }


    }

    fun deleteCourse(course: Course) {

        val builder1: AlertDialog.Builder = AlertDialog.Builder(MainActivity.context)
        builder1.setMessage(course.courseName)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Delete",
            DialogInterface.OnClickListener {

                    dialog, id ->
                showtableviewmodel.deleteCourse(course.courseCode + course.courseGroup, mTeacher)
                getData()

                dialog.cancel()

            })

        builder1.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }


    fun getseleted(ss: ArrayList<String>): ArrayList<String> {
        var s = course_checkbox_adapter.getSelectedCourse()
        return s
    }

    fun getData() {
        lifecycleScope.launch(Dispatchers.IO) {
            coursesList = async { showtableviewmodel.getTheChekedCourses(coursecodes) }.await()
        }


    }


}
/* val cources = ArrayList<Course>()
     val cource1 = Course("ds122", "DataStructure", "sundy", "11pm", "1pm", "h1", "d",null)
     val cource2 = Course("Al122", "Algorithms", "monday", "12pm", "2pm", "h2", "d",null)
     cources.add(cource1)
     cources.add(cource2)*/