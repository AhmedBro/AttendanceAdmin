package com.Fcih.attendance_admin.Desgin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.Data.Login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Navigation.findNavController(this.requireView())
                .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }


    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        auth = Firebase.auth

        val logBtn = view.findViewById<Button>(R.id.mLoginBtn)
        logBtn.setOnClickListener {
            mLoginProgressBar.visibility = View.VISIBLE
            var email = mEmailEt.text.toString()
            var password = mPasswordEt.text.toString()
            loginViewModel.login(email, password, auth)
        }

        var tv = view?.findViewById<TextView>(R.id.mForgetPassTv)
        tv?.setOnClickListener {
            Navigation.findNavController(this.requireView())
                .navigate(LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment())
        }


        loginViewModel.navigateToHome.observe(viewLifecycleOwner, Observer {
            if (it) {
                Navigation.findNavController(this.requireView())
                    .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                loginViewModel.doneNavigatingToHome()
            }
        })


        loginViewModel.showProgressbar.observe(viewLifecycleOwner, Observer {
            if (it) {
                mLoginProgressBar.visibility = View.VISIBLE
            } else {
                mLoginProgressBar.visibility = View.INVISIBLE
            }
        })

        loginViewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message.isNotEmpty()) {
                mErrorMessage.visibility = View.VISIBLE
                mErrorMessage.text = message
            } else {
                mErrorMessage.visibility = View.INVISIBLE
            }
        })


        return view
    }


    companion object {

    }
}