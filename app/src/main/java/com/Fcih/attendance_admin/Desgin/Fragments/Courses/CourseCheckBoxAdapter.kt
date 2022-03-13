package com.Fcih.attendance_admin.Desgin.Fragments.Courses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.course_checkbox_item_list.view.*

class CourseCheckBoxAdapter(var mylist: ArrayList<Course>):RecyclerView.Adapter<CourseCheckBoxAdapter.view_holder>()
{



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
       var v = LayoutInflater.from(parent.context).inflate(R.layout.course_checkbox_item_list,parent,false)
        return view_holder(v)
    }


    override fun onBindViewHolder(holder: view_holder, position: Int) {
var course_info = mylist[position]
        holder.name.text = course_info.courseName
        holder.code.text=course_info.courseCode
        holder.day.text=course_info.courseDate
        holder.from_date.text=course_info.courseTimeFrom
        holder.to_date.text=course_info.courseTimeTo
        holder.location.text=course_info.coursePlace
    }
    override fun getItemCount(): Int {
        return mylist.size
    }

    class view_holder(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        var name =itemview.mCheckCourseName
        var code =itemview.mCheckCourseCode
        var day =itemview.mCheckCourseDay
        var from_date= itemview.mCheckCourseFromDate
        var to_date = itemview.mCheckCourseToDate
        var location = itemview.mCheckCourseLocation

    }
}


