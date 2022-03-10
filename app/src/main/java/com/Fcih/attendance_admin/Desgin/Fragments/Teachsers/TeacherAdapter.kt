package com.Fcih.attendance_admin.Desgin.Fragments.Teachsers

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Desgin.Activities.MainActivity
import kotlinx.android.synthetic.main.list_item_teacher_view.view.*
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import com.Fcih.attendance_admin.Data.TeacherList.AddTeacherViewModel


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


           itemView.setOnLongClickListener {
               onItemLongClickListener?.let { it(getItem(position)) }
               return@setOnLongClickListener true
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
            return p0.equals(p1)
        }
    }

    private var onItemClickListener: ((Teacher) -> Unit)? = null
    private var onItemLongClickListener: ((Teacher) -> Unit)? = null
    fun setOnItemClickListener(listener: (Teacher) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: (Teacher) -> Unit) {
        onItemLongClickListener = listener
    }

}