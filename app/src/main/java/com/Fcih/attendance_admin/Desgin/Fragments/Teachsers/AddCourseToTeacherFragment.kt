package com.Fcih.attendance_admin.Desgin.Fragments.Teachsers

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.Fcih.attendance_admin.Data.CourseList.Course_checkBox
import com.Fcih.attendance_admin.Desgin.Fragments.Courses.CourseCheckBoxAdapter
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.fragment_add_course.*
import kotlinx.android.synthetic.main.fragment_add_course_to_teacher.*

class AddCourseToTeacherFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_course_to_teacher, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mylist =ArrayList<Course_checkBox>()
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
        mCheckBoxRecycler.adapter=my_adapter



    }


}