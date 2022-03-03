package com.Fcih.attendance_admin.View.Desgin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.Fcih.attendance_admin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddStudentsFragment : Fragment(R.layout.fragment_add_students) {
    val db = Firebase.firestore
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}
