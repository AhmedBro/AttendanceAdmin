package com.Fcih.attendance_admin.Data.TeacherList

import com.Fcih.attendance_admin.Data.CourseList.Course
import java.io.Serializable

class Teacher : Serializable{
    var id : String? = null
    var teacherName : String? = null
    var teacherPassword : String? = null
    var CoursesId : ArrayList<String>? = null

    constructor(){

    }

    constructor(
        id: String,
        teacherName: String?,
        teacherPassword: String?,
        Courses: ArrayList<String>?
    ) {
        this.id = id
        this.teacherName = teacherName
        this.teacherPassword = teacherPassword
        this.CoursesId = Courses
    }
}