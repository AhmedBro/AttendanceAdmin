package com.Fcih.attendance_admin.Data.TeacherList

import com.Fcih.attendance_admin.Data.CourseList.Course

data class Teacher(var id : String , var teacherName : String , var teacherPassword : String , var Courses : ArrayList<Course>?)