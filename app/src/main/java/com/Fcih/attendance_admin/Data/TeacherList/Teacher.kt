package com.Fcih.attendance_admin.Data.TeacherList

import com.Fcih.attendance_admin.Data.CourseList.Course

class Teacher{
    var id : String? = null
    var teacherName : String? = null
    var teacherPassword : String? = null
    var Courses : ArrayList<Course>? = null

    constructor(){

    }

    constructor(
        id: String?,
        teacherName: String?,
        teacherPassword: String?,
        Courses: ArrayList<Course>?
    ) {
        this.id = id
        this.teacherName = teacherName
        this.teacherPassword = teacherPassword
        this.Courses = Courses
    }
}