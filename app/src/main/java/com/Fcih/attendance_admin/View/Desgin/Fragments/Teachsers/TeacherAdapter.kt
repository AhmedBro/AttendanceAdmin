package com.Fcih.attendance_admin.View.Desgin.Fragments.Teachsers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.View.Data.TeacherList.Teacher
import kotlinx.android.synthetic.main.list_item_teacher_view.view.*


class TeacherAdapter() :
    androidx.recyclerview.widget.ListAdapter<Teacher, TeacherAdapter.TeacherViewHolder>(
        TeacherDiffCallback()
    ) {

    //         RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_teacher_view, parent, false)
        return TeacherViewHolder(view)
    }


    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.apply {
            name.text = getItem(position).teacherName
            itemView.setOnClickListener {
                onItemClickListener?.let { it(getItem(position)) }
            }
        }
    }

    inner class TeacherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.mTeacherName
    }

    class TeacherDiffCallback : DiffUtil.ItemCallback<Teacher>() {
        override fun areItemsTheSame(p0: Teacher, p1: Teacher): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(p0: Teacher, p1: Teacher): Boolean {
            return p0 == p1
        }
    }

    private var onItemClickListener: ((Teacher) -> Unit)? = null

    fun setOnItemClickListener(listener: (Teacher) -> Unit) {
        onItemClickListener = listener
    }

}