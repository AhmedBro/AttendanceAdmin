package com.Fcih.attendance_admin.Data.Students

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.Desgin.Activities.MainActivity
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore

class SearchStudentViewModel : ViewModel() {


    private val _showProgressbar = MutableLiveData<Boolean>()
    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    private val _error = MutableLiveData<String>()
    val error : LiveData<String>
        get() = _error


    suspend fun getStudent(year: String, id:String) {
        InitFireStore.instance.collection(Constants.STUDENTS_TABLE).document(year)
            .collection(Constants.STUDENT_DATA).document(id)
            .get().addOnSuccessListener {
                _showProgressbar.value = false
                _success.value = it.toObject(Student::class.java)?.StudentID != null
            }
            .addOnFailureListener {
                _showProgressbar.value = false

                _error.value = it.localizedMessage.toString()
            }
    }


    fun deleteCourse(year: String, id:String ) {
        InitFireStore.instance.collection(Constants.STUDENTS_TABLE).document(year)
            .collection(Constants.STUDENT_DATA).document(id).delete().addOnSuccessListener {

                Toast.makeText(MainActivity.context, "Student has been deleted", Toast.LENGTH_LONG)
                    .show()

            }
            .addOnFailureListener {
                Toast.makeText(MainActivity.context, it.message, Toast.LENGTH_LONG).show()

            }
    }

}