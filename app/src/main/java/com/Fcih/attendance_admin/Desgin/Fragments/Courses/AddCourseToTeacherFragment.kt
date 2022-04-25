package com.Fcih.attendance_admin.Desgin.Fragments.Courses

import android.annotation.SuppressLint
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
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.Data.CourseList.CourseCheckBoxViewModel
import com.Fcih.attendance_admin.Data.CourseList.CourseListViewModel
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Desgin.Fragments.Teachsers.ShowTeacherTableFragmentArgs
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.fragment_add_course_to_teacher.*
import kotlinx.android.synthetic.main.fragment_course_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddCourseToTeacherFragment : Fragment() {

    lateinit var course_checkbox_ViewModel: CourseCheckBoxViewModel
    lateinit var coursesList: ArrayList<Course>
    lateinit var adapter: CourseCheckBoxAdapter
    lateinit var course_ViewModel: CourseListViewModel
    lateinit var mTeacher: Teacher


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mTeacher = AddCourseToTeacherFragmentArgs.fromBundle(requireArguments()).teacherData

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_course_to_teacher, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.mCheckBoxRecycler)
        rv.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        course_checkbox_ViewModel = ViewModelProvider(this).get(CourseCheckBoxViewModel::class.java)
        course_checkbox_ViewModel.showProgressBar()
        course_checkbox_ViewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        course_checkbox_ViewModel.showProgressbar.observe(viewLifecycleOwner, Observer {
            if (it) {
                mCheckBoxCoursesProgressBar.visibility = View.VISIBLE
            } else {
                mCheckBoxCoursesProgressBar.visibility = View.GONE

            }
        })
        course_checkbox_ViewModel.doneRetrieving.observe(viewLifecycleOwner, Observer {
            if (it) {
                if (coursesList.size == 0) {
                    mChexBoxNoCoursesTV.visibility = View.VISIBLE
                } else {
                    mChexBoxNoCoursesTV.visibility = View.GONE
                }
                adapter = CourseCheckBoxAdapter(coursesList)
                course_checkbox_ViewModel.doneRetrievingdata()
                rv.adapter = adapter
                // in this step we grap the courses which is already in teacher table to add it
                //to the the selected courses because firestore doesn't allow add new item in array


                mTeacher = AddCourseToTeacherFragmentArgs.fromBundle(requireArguments()).teacherData
                var courses = mTeacher.CoursesId
                val addedselected = adapter.selectedCourses










                mAddCourseToTeacherBtn.setOnClickListener {

                    var justseleted = adapter.selectedCourses

                    if (courses != null) {
                        course_checkbox_ViewModel.addCourseToteacher(
                            mTeacher,
                            courses,
                            addedselected
                        )
                    }
                    if (courses == null) {
                        var ss: ArrayList<String> = ArrayList()
                        course_checkbox_ViewModel.addCourseToteacher(mTeacher, ss, addedselected)
                    }




                    for (i in addedselected) {
                        Log.d("justselected", i)
                    }

                }


            }


        })






        return view
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()


        /*   var mylist =ArrayList<Course_checkBox>()
           var course2 = Course_checkBox("math1","11","sunday","2Pm","4pm","h2")
           var course3 = Course_checkBox("math1","11","sunday","2Pm","4pm","h2")

           var course4 = Course_checkBox("math1","11","sunday","2Pm","4pm","h2")

           var course1 = Course_checkBox("math1","11","sunday","2Pm","4pm","h2")

           mylist.add(course1)
           mylist.add(course2)
           mylist.add(course3)
           mylist.add(course4)
           mCheckBoxRecycler.layoutManager=LinearLayoutManager(requireContext(), LinearLayout.VERTICAL,false)

           var my_adapter = CourseCheckBoxAdapter(mylist)
           mCheckBoxRecycler.adapter=my_adapter*/


    }


    fun getData() {
        lifecycleScope.launch(Dispatchers.IO) {
            coursesList = async { course_checkbox_ViewModel.getAllChekBoxCourses() }.await()
        }


    }

}