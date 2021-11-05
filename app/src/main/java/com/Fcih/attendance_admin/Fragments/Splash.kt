package com.Fcih.attendance_admin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.Fcih.attendance_admin.Database.SharedPreferences
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.Activities.MainActivity

import android.content.Intent
import android.os.Handler
import androidx.navigation.Navigator


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Splash.newInstance] factory method to
 * create an instance of this fragment.
 */
class Splash : Fragment() {
    lateinit var mSharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSharedPreferences = context?.let { SharedPreferences(it) }!!
        gotoNextPage()
    }

    private fun gotoNextPage() {
        Handler().postDelayed(Runnable {
            if (mSharedPreferences.IsLogin()) {

                findNavController().navigate(R.id.action_splash_to_homeFragment)


            } else {
                findNavController().navigate(R.id.action_splash_to_loginFragment)

            }
        }, 2000L)

    }



    companion object {

    }
}