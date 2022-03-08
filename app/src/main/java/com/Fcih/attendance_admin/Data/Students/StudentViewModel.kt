package com.Fcih.attendance_admin.Data.Students

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore
import java.time.Year

class StudentViewModel : ViewModel() {


    private val _showProgressbar = MutableLiveData<Boolean>()
    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar


    private val _Message = MutableLiveData<String>()
    val Message: LiveData<String>
        get() = _Message

    fun AddStudent(Student: Student, year: String , endValue : Int) {
        InitFireStore.instance.collection(Constants.STUDENTS_TABLE).document(year)
            .collection(Constants.STUDENT_DATA).document(Student.StudentID!!)
            .set(Student).addOnSuccessListener {
                if (Integer.parseInt(Student.StudentID) == endValue){
                    _showProgressbar.value = false
                }
                _Message.value = "student Added Successfully"
            }
            .addOnFailureListener {
                _showProgressbar.value = false
                _Message.value = it.message.toString()
            }
    }
}