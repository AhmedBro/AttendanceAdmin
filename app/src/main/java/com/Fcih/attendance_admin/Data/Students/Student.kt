package com.Fcih.attendance_admin.Data.Students

class Student {
    var StudentID: String? = null
    var StudentPassword: String? = null
    var CoursesId :ArrayList<String>? = null

    constructor(StudentID: String?, StudentPassword: String?, CoursesId: ArrayList<String>?) {
        this.StudentID = StudentID
        this.StudentPassword = StudentPassword
        this.CoursesId = CoursesId
    }
}