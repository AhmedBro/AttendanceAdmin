package com.Fcih.attendance_admin.Data.CourseList

import java.io.Serializable


class Course :Serializable  {
     var courseCode : String? = null
    var courseName: String?= null
    var courseDate: String?= null
    var courseTimeFrom: String?= null
    var courseTimeTo: String?= null
    var coursePlace: String?= null
    var courseGroup: String?= null
    var Lectuers:ArrayList<Lectuers>?= null
    constructor()


   //for fill course table
    constructor(
        courseCode: String?,
        courseName: String?,
        courseDate: String?,
        courseTimeFrom: String?,
        courseTimeTo: String?,
        coursePlace: String?,
        courseGroup: String?,
        Lectuers: ArrayList<Lectuers>?
    ) {
        this.courseCode = courseCode
        this.courseName = courseName
        this.courseDate = courseDate
        this.courseTimeFrom = courseTimeFrom
        this.courseTimeTo = courseTimeTo
        this.coursePlace = coursePlace
        this.courseGroup = courseGroup
        this.Lectuers = Lectuers
    }
}
