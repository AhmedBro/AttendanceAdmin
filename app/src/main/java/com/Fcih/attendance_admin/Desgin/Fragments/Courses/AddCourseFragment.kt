package com.Fcih.attendance_admin.Desgin.Fragments.Courses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.Fcih.attendance_admin.Data.CourseList.CourseListViewModel
import com.Fcih.attendance_admin.Data.Login.LoginViewModel
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.fragment_add_course.*
import kotlinx.android.synthetic.main.fragment_add_students.*


class AddCourseFragment : Fragment(R.layout.fragment_add_course) {
lateinit var courseGroup :String
lateinit var courseViewModel: CourseListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var courseName = mCourseNameEt.text.toString()
        var courseCode = mCourseCodeEt.text.toString()
        var courseDate = mCourseDateEt.text.toString()
        var courseStartTime = mStartTimeEt.text.toString()
        var courseEndTime = mEndTimeEt.text.toString()
        var coursePlace = mCoursePlaceEt.text.toString()

        mGroupsp.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                courseGroup= parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        courseViewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)

        mAddCourseBtn.setOnClickListener {
            mAddCourseProgressBar.visibility=View.VISIBLE

            TODO(" complete validation function $  addCourse function")

        }

        courseViewModel.isSuccess.observe(viewLifecycleOwner, Observer {
            if (it){
                TODO("Add navigation & doneNavigation Function")
            }
        })

        courseViewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                Toast.makeText(activity,it,Toast.LENGTH_LONG).show()
            }
        })

        courseViewModel.showProgressbar.observe(viewLifecycleOwner, Observer {
            if (!it){
                mAddCourseProgressBar.visibility= View.GONE
            }
        })




    }



    fun Validate(): Boolean {
        if (mCourseNameEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter course name", Toast.LENGTH_LONG).show()
            return false
        } else if (mCourseCodeEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter course code", Toast.LENGTH_LONG).show()
            return false
        } else if (mCoursePlaceEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter course place", Toast.LENGTH_LONG).show()
            return false
        } else return true


    }
}