package com.Fcih.attendance_admin.Desgin.Fragments.Teachsers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.Fcih.attendance_admin.Data.TeacherList.AddTeacherViewModel
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.fragment_add_students.*
import kotlinx.android.synthetic.main.fragment_add_teacher.*

class AddTeacher : Fragment(R.layout.fragment_add_teacher) {


    lateinit var addTeacherViewModel: AddTeacherViewModel
    var teacherName: String? = null
    var teacherCode: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTeacherViewModel = ViewModelProvider(this).get(AddTeacherViewModel::class.java)

        mAddTeacherBtn.setOnClickListener {
            mAddTeacherProgressBar.visibility = View.VISIBLE
            if (Validate()) {
                var teacherName = edit_teacher_name.text.toString()
                var teacherCode = edit_teacher_code.text.toString()

                addTeacherViewModel.addTeacher(Teacher(teacherCode, teacherName, teacherCode))

            } else {
                mAddTeacherProgressBar.visibility = View.GONE
            }
        }


        mShowTeachersTv.setOnClickListener {
            Navigation.findNavController(this.requireView())
                .navigate(AddTeacherDirections.actionAddTeacherToTeacherListFragment())
//            Toast.makeText(application, "Clicked", Toast.LENGTH_SHORT).show()
        }

        addTeacherViewModel.showProgressbar.observe(viewLifecycleOwner, Observer {
            if (!it) {
                mAddTeacherProgressBar.visibility = View.GONE
                edit_teacher_name.text.clear()
                edit_teacher_code.text.clear()
                Toast.makeText(activity, "Teacher has been added.", Toast.LENGTH_LONG).show()
            }
        })

    }


    fun Validate(): Boolean {

        if (edit_teacher_name.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter Teacher Name", Toast.LENGTH_LONG).show()
            return false
        } else if (edit_teacher_code.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter Teacher Code", Toast.LENGTH_LONG).show()
            return false
        } else return true

    }

}