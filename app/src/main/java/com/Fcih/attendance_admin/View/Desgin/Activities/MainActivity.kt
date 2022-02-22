package com.Fcih.attendance_admin.View.Desgin.Activities

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.Fcih.attendance_admin.R

class MainActivity : AppCompatActivity() {

    companion object{
         var context : Activity? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

init {
    context =this
}
}