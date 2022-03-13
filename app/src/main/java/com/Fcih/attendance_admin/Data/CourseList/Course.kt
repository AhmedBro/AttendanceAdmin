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

   /*for fill out show teacher table just in design, and it's a temporal step till i add courses to teacher
    from checkbox and then i won't use this constructor anymore coz i will fill show_teacher_table_RV from Teachr
     table in database
    */
    constructor(
        courseCode: String?,
        courseName: String?,
        courseDate: String?,
        courseTimeFrom: String?,
        courseTimeTo: String?,
        coursePlace: String?,
        courseGroup: String?,

    ){
        this.courseCode = courseCode
        this.courseName = courseName
        this.courseDate = courseDate
        this.courseTimeFrom = courseTimeFrom
        this.courseTimeTo = courseTimeTo
        this.coursePlace = coursePlace
        this.courseGroup = courseGroup
    }
    //*********************************************************************************

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
