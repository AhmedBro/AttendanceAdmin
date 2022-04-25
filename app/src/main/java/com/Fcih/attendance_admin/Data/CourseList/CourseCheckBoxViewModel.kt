package com.Fcih.attendance_admin.Data.CourseList

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.Fcih.attendance_admin.Data.TeacherList.Teacher
import com.Fcih.attendance_admin.Desgin.Fragments.Courses.AddCourseToTeacherFragmentDirections
import com.Fcih.attendance_admin.Desgin.Fragments.Courses.CourseCheckBoxAdapter
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore
import kotlin.math.log


class CourseCheckBoxViewModel : ViewModel() {
    var context = this
    lateinit var adapter: CourseCheckBoxAdapter
    var redundantFlag :Boolean = false
    var addFlag :Boolean = false
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess
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


    suspend fun getAllChekBoxCourses(): ArrayList<Course> {
        var courses = ArrayList<Course>()
        InitFireStore.instance.collection(Constants.COURSES_TABLE)
            .get().addOnSuccessListener {
                for (course in it) {
                    var newCourse = course.toObject(Course::class.java)

                    Log.d(newCourse.courseCode, "MennaTest")
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


    fun addCourseToteacher(
        teaccher: Teacher,
        allcourses: ArrayList<String>,
        addedselected: ArrayList<String>
    ) {
        var s: String? = teaccher.teacherName + teaccher.id

        if (s != null) {
            if (addedselected.size != 0) {
                if (allcourses.isNotEmpty()) {
                    for (i in addedselected) {
                        if (!allcourses.contains(i)) {
                            allcourses.add(i)
                            addFlag = true
                        }else{
                            redundantFlag = true
                        }
                    }
                    InitFireStore.instance.collection(Constants.TEACHER_TABLE).document(s)
                        .update("coursesId", allcourses)
                        .addOnSuccessListener {
                            _showProgressbar.value = false
                            if (addFlag!! && redundantFlag!!){
                                _error.value = "Only new courses have been added"
                            }else if(addFlag!!){
                                _error.value = "Course added successfully"
                            }else if (redundantFlag!!){
                                _error.value = "Course already exist"
                            }
                            _isSuccess.value = true
                        }
                        .addOnFailureListener {
                            _showProgressbar.value = false
                            _error.value = it.message.toString()
                            _isSuccess.value = false
                        }

                } else {
                    teaccher.CoursesId = addedselected

                    InitFireStore.instance.collection(Constants.TEACHER_TABLE).document(s)
                        .set(teaccher)
                        .addOnSuccessListener {
                            _showProgressbar.value = false
                            _error.value = "Course Added Successfully"
                            _isSuccess.value = true
                        }
                        .addOnFailureListener {
                            _showProgressbar.value = false
                            _error.value = it.message.toString()
                            _isSuccess.value = false
                        }
                }


            }
            if (addedselected.size == 0) {
                _error.value = "Please select Teacher's courses"
            }
        }
    }


    /* val washingtonRef = db.collection("cities").document("DC")

// Set the "isCapital" field of the city 'DC'

         .update("capital", true)
         .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
         .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }*/

    /* teaccher.id?.let {
         InitFireStore.instance.collection(Constants.TEACHER_TABLE).document(it)
             .set(teaccher).addOnSuccessListener {
                 _showProgressbar.value = false
                 _error.value = "Course Added Successfully"
                 _isSuccess.value=true
             }
             .addOnFailureListener {
                 _showProgressbar.value = false
                 _error.value = it.message.toString()
                 _isSuccess.value=false
             }
     }*/


    fun doneNavigate() {
        _isSuccess.value = false
    }


}