package com.Fcih.attendance_admin.Data.TeacherList

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Fcih.attendance_admin.Desgin.Activities.MainActivity
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TeacherListViewModel : ViewModel() {

    private val _showProgressbar = MutableLiveData<Boolean>()
    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar

    private val _doneRetrieving = MutableLiveData<Boolean>()
    val doneRetrieving: LiveData<Boolean>
        get() = _doneRetrieving

    private val _showNoTeachers = MutableLiveData<Boolean>()
    val showNoTeachers: LiveData<Boolean>
        get() = _showNoTeachers


    fun doneRetrievingData() {
        _doneRetrieving.value = false
    }

    fun doneShowingProgressBar() {
        _showProgressbar.value = true
    }

    fun showingProgressBar() {
        _showProgressbar.value = true
    }

    fun hideNoTeacherMessage() {
        _showNoTeachers.value = false
    }


    suspend fun getTeachers(): ArrayList<Teacher> {

        var teachers = ArrayList<Teacher>()

        InitFireStore.instance.collection(Constants.TEACHER_TABLE)
            .get()
            .addOnSuccessListener { result ->
                _showNoTeachers.value = result.documents.size == 0
                for (document in result) {
                    teachers.add(document.toObject<Teacher>())
                }
                _doneRetrieving.value = true
                _showProgressbar.value = false

            }
            .addOnFailureListener { exception ->
                Log.e("Adminnnnnnnnnnnnnnnnn", "Error getting documents: ", exception)
                _showProgressbar.value = false
            }
        return teachers
    }

    fun DeleteDoc(teacher: Teacher) {
        InitFireStore.instance.collection(Constants.TEACHER_TABLE)
            .document(teacher.teacherName + teacher.id).delete().addOnSuccessListener {

                Toast.makeText(MainActivity.context , "Doctor has been deleted" , Toast.LENGTH_LONG).show()

            }
            .addOnFailureListener {
                Toast.makeText(MainActivity.context , it.message , Toast.LENGTH_LONG).show()

            }
    }


}