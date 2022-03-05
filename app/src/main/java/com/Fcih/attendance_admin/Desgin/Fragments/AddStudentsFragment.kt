package com.Fcih.attendance_admin.Desgin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.Fcih.attendance_admin.Data.Login.LoginViewModel
import com.Fcih.attendance_admin.Data.Students.Student
import com.Fcih.attendance_admin.Data.Students.StudentViewModel
import com.Fcih.attendance_admin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_add_students.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates


class AddStudentsFragment : Fragment(R.layout.fragment_add_students) {
    lateinit var AddStudents: StudentViewModel
    lateinit var student: Student
    var mStartValue: Int? = null
    var mEndValue: Int? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AddStudents = ViewModelProvider(this).get(StudentViewModel::class.java)

        mAddStudents.setOnClickListener {
            if (Validate()) {
                mStartValue = Integer.parseInt(mFirdtIdEt.text.toString())
                mEndValue = Integer.parseInt(mLastIdEt.text.toString())
                lifecycleScope.launch(Dispatchers.IO) {
                    for (i in mStartValue!!..mEndValue!!) {
                        student = Student(i.toString(), i.toString())
                        AddStudents.AddStudent(student, mYearEt.text.toString())
                    }

                }
            }
        }

    }

    fun Validate(): Boolean {

        if (mFirdtIdEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter First id", Toast.LENGTH_LONG).show()
            return false
        } else if (mLastIdEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter last id", Toast.LENGTH_LONG).show()
            return false
        } else if (mYearEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter Academic Year", Toast.LENGTH_LONG).show()
            return false
        } else return true

    }
}