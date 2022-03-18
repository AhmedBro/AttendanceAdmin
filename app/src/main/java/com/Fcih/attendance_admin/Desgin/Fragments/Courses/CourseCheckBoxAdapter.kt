package com.Fcih.attendance_admin.Desgin.Fragments.Courses

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.course_checkbox_item_list.view.*
import kotlinx.android.synthetic.main.fragment_add_course_to_teacher.view.*

class CourseCheckBoxAdapter(var mylist: ArrayList<Course>):RecyclerView.Adapter<CourseCheckBoxAdapter.view_holder>()
{


   var selectedCourses: ArrayList<String> =ArrayList<String>()

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
        var i = course_info.courseCode.toString()
        var c =course_info.courseGroup.toString()


holder.checkbox.setOnClickListener{
    if (holder.checkbox.isChecked==true)
    selectedCourses.add(i + c)
    else
        selectedCourses.remove(i + c)

    }







  /*  holder.thelayout.setOnClickListener {
        if( holder.checkbox.isChecked == true) {
            holder.checkbox.isChecked = false
        selectedCourses.remove(i + c)

    }
        else{
            holder.checkbox.isChecked = true
            selectedCourses.add(i + c)
        }
        }*/

      /*  holder.thelayout.setOnClickListener {
            if( holder.checkbox.isChecked==true) {
                selectedCourses.remove(i + c)
                holder.checkbox.isChecked = false

            }
        }*/





        // Log.d("selecid","before selected")
        //Log.d("selecid",i)
        // holder.thelayout.setBackgroundResource(R.drawable.selector2)

    }
    override fun getItemCount(): Int {
        return mylist.size
    }
    fun getSelectedCourse(): ArrayList<String> {
        return selectedCourses
    }

    class view_holder(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        var name =itemview.mCheckCourseName
        var code =itemview.mCheckCourseCode
        var day =itemview.mCheckCourseDay
        var from_date= itemview.mCheckCourseFromDate
        var to_date = itemview.mCheckCourseToDate
        var location = itemview.mCheckCourseLocation
        val thelayout =itemview.mCheckBoxItemList
        var checkbox = itemview.mCheckBoxCourse




    }
}


