package com.Fcih.attendance_admin.Data.CourseList

import java.io.Serializable

class Course_checkBox:Serializable{

    var name:String?="test"
    var code:String?="test"
    var day:String?="test"
    var DateFrom:String?="test"
    var ToDate:String?=null
    var Location:String?=null


     constructor(
          name:String?,
          code:String?,
          day:String?,
        DateFrom:String?,
         ToDate:String?,
         Location:String?

         ){
         this.name=name
         this.code=code
         this.day=day
         this.DateFrom=DateFrom
         this.ToDate=ToDate
         this.ToDate=Location
     }

    constructor()

}

