package com.Fcih.attendance_admin.View.Desgin.Fragments.Courses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.View.Data.CourseList.Course
import kotlinx.android.synthetic.main.list_item_course_view.view.*
import kotlinx.android.synthetic.main.list_item_teacher_view.view.*


class CourseAdapter() :
    androidx.recyclerview.widget.ListAdapter<Course, CourseAdapter.CourseViewHolder>(
        CourseDiffCallback()
    ) {

    //         RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_course_view, parent, false)
        return CourseViewHolder(view)
    }


    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.apply {
            courseCode.text = getItem(position).courseCode
            courseName.text = getItem(position).courseName
            courseDay.text = getItem(position).courseDay
            courseTime.text = getItem(position).courseTime
            coursePlace.text = getItem(position).coursePlace
            itemView.setOnClickListener {
                onItemClickListener?.let { it(getItem(position)) }
            }
        }
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var courseCode = itemView.mCourseCode
        var courseName = itemView.mCourseName
        var courseDay = itemView.mCourseDay
        var courseTime = itemView.mCourseTime
        var coursePlace = itemView.mCourseLocation
    }

    class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(p0: Course, p1: Course): Boolean {
            return p0.courseCode == p1.courseCode
        }

        override fun areContentsTheSame(p0: Course, p1: Course): Boolean {
            return p0 == p1
        }
    }

    private var onItemClickListener: ((Course) -> Unit)? = null

    fun setOnItemClickListener(listener: (Course) -> Unit) {
        onItemClickListener = listener
    }

}