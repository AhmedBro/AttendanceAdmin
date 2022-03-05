package com.Fcih.attendance_admin.Data.Students

class Student {
   var StudentID:String? = null
    var StudentPassword:String? = null

    constructor(StudentID: String?, StudentPassword: String?) {
        this.StudentID = StudentID
        this.StudentPassword = StudentPassword
    }
}