package com.Fcih.attendance_admin.Desgin.Fragments.Teachsers

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.Data.CourseList.Lectuers
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Desgin.Fragments.Courses.CourseListAdapter
import com.Fcih.attendance_admin.Desgin.Fragments.Courses.CourseListFragmentDirections
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.fragment_show_teacher_table.*


class ShowTeacherTableFragment : Fragment() {
    lateinit var mTeacher: Teacher

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // mTableRecyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayout.VERTICAL,false)


        return inflater.inflate(R.layout.fragment_show_teacher_table, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var linearLayoutManager = LinearLayoutManager(this.context)
        mTeacher =    ShowTeacherTableFragmentArgs.fromBundle(requireArguments()).teacherData

        mTableRecyclerView.layoutManager = linearLayoutManager
        val cources = ArrayList<Course>()
        val cource1 = Course("ds122", "DataStructure", "sundy", "11pm", "1pm", "h1", "d")
        val cource2 = Course("Al122", "Algorithms", "monday", "12pm", "2pm", "h2", "d")
        cources.add(cource1)
        cources.add(cource2)


        val courseAdapter = CourseListAdapter(cources)

        mTableRecyclerView.adapter = courseAdapter

        mAddTeacherCourseTv.setOnClickListener {
            Navigation.findNavController(this.requireView())
                .navigate(ShowTeacherTableFragmentDirections.actionShowTeacherTableFragmentToAddCourseToTeacherFragment())

        }
    }


}