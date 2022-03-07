package com.Fcih.attendance_admin.Data.TeacherList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore
import kotlinx.coroutines.*

class AddTeacherViewModel() : ViewModel() {

    private val viewModelJob = Job()

    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _showProgressbar = MutableLiveData<Boolean>()
    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar


    fun addTeacher(teacher: Teacher) {
        ioScope.launch {
            InitFireStore.instance.collection(Constants.TEACHER_TABLE).document(teacher.teacherName + teacher.id)
                .collection(teacher.id).document(Constants.STUDENT_PERSONAL_DATA)
                .set(teacher).addOnSuccessListener {
                    _showProgressbar.value = false
                }
                .addOnFailureListener {
                    _showProgressbar.value = false
                }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}