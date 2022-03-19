package com.Fcih.attendance_admin.Data.CourseList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore

class ShowTeacherTableViewModel : ViewModel() {
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


    private val _doneDeleting = MutableLiveData<Boolean>()
    val doneDeleting: LiveData<Boolean>
        get() = _doneDeleting

    fun doneDeletingCourse(){
        _doneDeleting.value = false
    }

    fun doneRetrievingdata() {
        _doneRetrieving.value = false
    }

    fun showProgressBar() {
        _showProgressbar.value = true
    }


    suspend fun getTheChekedCourses(cousecod: ArrayList<String>?): ArrayList<Course> {
        var courses = ArrayList<Course>()
        InitFireStore.instance.collection(Constants.COURSES_TABLE)
            .get().addOnSuccessListener {
                for (course in it) {
                    val newCourse = course.toObject(Course::class.java)
                    if (cousecod != null) {
                        var i = newCourse.courseCode.toString() + newCourse.courseGroup.toString()
                        if (i in cousecod)
                            courses.add(newCourse)
                    }
                }

                _doneRetrieving.value = true
                _showProgressbar.value = false

            }.addOnFailureListener {
                _error.value = it.message
                _showProgressbar.value = false
            }
        return courses
    }


    fun deleteCourse(courseId: String?, mTeacher: Teacher){
        _showProgressbar.value = true
        InitFireStore.instance.collection(Constants.TEACHER_TABLE)
            .document(mTeacher.teacherName + mTeacher.id)
            .get().addOnSuccessListener {
                var teacher = it.toObject(Teacher::class.java)
                if (courseId != null) {
                    if (teacher != null) {
                        teacher.CoursesId!!.remove(courseId)
                        InitFireStore.instance.collection(Constants.TEACHER_TABLE)
                            .document(mTeacher.teacherName + mTeacher.id)
                            .set(teacher).addOnSuccessListener {
                                _doneDeleting.value = true
                            }
                    }
                }
            }.addOnFailureListener {
                _error.value = it.message
            }
    }

}