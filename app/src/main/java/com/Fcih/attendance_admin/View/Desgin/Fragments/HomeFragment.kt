package com.Fcih.attendance_admin.View.Desgin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.Fcih.attendance_admin.R

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val show_btn = view.findViewById<AppCompatButton>(R.id.show_teach_tbl)

        show_btn.setOnClickListener {
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTeacherListFragment())
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    companion object {

    }
}