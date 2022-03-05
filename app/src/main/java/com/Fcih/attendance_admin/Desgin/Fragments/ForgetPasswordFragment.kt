package com.Fcih.attendance_admin.Desgin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.Data.ForgetPassword.ForgetPasswordViewModel
import com.google.firebase.auth.FirebaseAuth


class ForgetPasswordFragment : Fragment(R.layout.fragment_forget_password) {

    lateinit var mNavController: NavController
    lateinit var auth: FirebaseAuth
    lateinit var forgetPasswordViewModel: ForgetPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmit = view.findViewById<Button>(R.id.mSubmitForgetPasswordBtn)
        auth = FirebaseAuth.getInstance()
        forgetPasswordViewModel = ViewModelProvider(this).get(ForgetPasswordViewModel::class.java)

        btnSubmit.setOnClickListener {
            var email: String =
                view.findViewById<EditText>(R.id.mEnterEmailEt).text.toString().trim { it <= ' ' }
            forgetPasswordViewModel.forgetPassword(email, auth)
        }

        forgetPasswordViewModel.isSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                mNavController.navigate(ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToLoginFragment())
                forgetPasswordViewModel.done()
            }
        })

        forgetPasswordViewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                Toast.makeText(this.context,it,Toast.LENGTH_SHORT).show()
            }
        })


    }


}