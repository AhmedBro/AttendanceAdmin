package com.Fcih.attendance_admin.Data.CourseList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore

class CourseListViewModel : ViewModel() {

    private val _showProgressbar = MutableLiveData<Boolean>()
    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private val _error = MutableLiveData<String>()
    val error : LiveData<String>
        get() = _error

   suspend fun addCourse(course: Course){
       InitFireStore.instance.collection(Constants.COURSES_TABLE).document(course.courseCode+""+course.courseGroup)
           .set(course).addOnSuccessListener {
               _showProgressbar.value = false
               _error.value = "Course Added Successfully"
               _isSuccess.value=true
           }
           .addOnFailureListener {
               _showProgressbar.value = false
               _error.value = it.message.toString()
               _isSuccess.value=false
           }

   }

    fun doneNavigate(){
        _isSuccess.value=false
    }
}