package com.Fcih.attendance_admin.Desgin.Fragments.Courses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.list_item_course_view.view.*

class CourseListAdapter(var allCoursesList : List<Course>?) : RecyclerView.Adapter<CourseListAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_course_view, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.itemView
        holder.itemView.apply {
            mCourseName.text=allCoursesList!![position].courseName
            mCourseCode.text=allCoursesList!![position].courseCode
            mCourseDay.text=allCoursesList!![position].courseDate
            mCourseTimeTo.text= allCoursesList!![position].courseTimeFrom
            mCourseTimeFrom.text = allCoursesList!![position].courseTimeTo
            mCourseLocation.text=allCoursesList!![position].coursePlace

        }
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.let { it(allCoursesList!![position]) }
            return@setOnLongClickListener true
        }
    }
    override fun getItemCount(): Int {
        return allCoursesList!!.size
    }

    private var onItemLongClickListener: ((Course) -> Unit)? = null
    fun setOnItemLongClickListener(listener: (Course) -> Unit) {
        onItemLongClickListener = listener
    }
}