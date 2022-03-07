package com.Fcih.attendance_admin.Data.CourseList

data class Course(
    var courseCode: String,
    var courseName: String,
    var courseDate: String,
    var courseTimeFrom: String,
    var courseTimeTo: String,
    var coursePlace: String,
    var courseGroup: String
)