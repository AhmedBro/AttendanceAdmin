package com.Fcih.attendance_admin.Data.CourseList

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Desgin.Activities.MainActivity
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore

class CourseListViewModel : ViewModel() {

    private val _showProgressbar = MutableLiveData<Boolean>()
    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar

//    private val _coursesListData : MutableLiveData<MutableList<Course>> = MutableLiveData(mutableListOf())
//    val coursesListData:LiveData<MutableList<Course>>
//    get() = _coursesListData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _doneRetrieving = MutableLiveData<Boolean>()
    val doneRetrieving: LiveData<Boolean>
        get() = _doneRetrieving

    fun doneRetrievingdata() {
        _doneRetrieving.value = false
    }

    fun showProgressBar() {
        _showProgressbar.value = true
    }


    suspend fun getAllCourses(): ArrayList<Course> {
        var courses = ArrayList<Course>()
        InitFireStore.instance.collection(Constants.COURSES_TABLE)
            .get().addOnSuccessListener {
                /*he will go through the returned collection document by document
                So this(course) which we use as iterative refers to every doc in
                the returned collection*/
                for (course in it) {
                    var newCourse = course.toObject(Course::class.java)
                    courses.add(newCourse)
                }
                _doneRetrieving.value = true
                _showProgressbar.value = false

            }.addOnFailureListener {
                _error.value = it.message
                _showProgressbar.value = false
            }
        return courses
    }
    fun deleteCourse(course: Course) {
        InitFireStore.instance.collection(Constants.COURSES_TABLE)
            .document(course.courseCode + course.courseGroup).delete().addOnSuccessListener {

                Toast.makeText(MainActivity.context, "Course has been deleted", Toast.LENGTH_LONG)
                    .show()

            }
            .addOnFailureListener {
                Toast.makeText(MainActivity.context, it.message, Toast.LENGTH_LONG).show()

            }
    }
}