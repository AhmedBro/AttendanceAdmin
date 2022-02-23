package com.Fcih.attendance_admin.View.Desgin.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.Fcih.attendance_admin.R
import com.google.firebase.auth.FirebaseAuth


class ForgetPasswordFragment : Fragment(R.layout.fragment_forget_password) {

    lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController=findNavController()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSubmit =view.findViewById<Button>(R.id.mSubmitForgetPasswordBtn)

        btnSubmit.setOnClickListener {
            var email :String =view.findViewById<EditText>(R.id.mEnterEmailEt).text.toString().trim {it<= ' '}

            if (email.isEmpty()){
                Log.d("testmail","empty mail")
                Toast.makeText(this.context,"you have to enter email !!",Toast.LENGTH_LONG).show()
            }
            else{
                Log.d("testmail", email)
                Log.d("testmail", "out the fun")
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                    Log.d("testmail", "in the fun")

                    if (it.isSuccessful){

                        Log.d("testmail", email)
                        Log.d("testmail", "fun successfully")
                        Toast.makeText(this.context,"email sent successfully ",Toast.LENGTH_LONG).show()

                        // Action to Login Fragment
                        var action=ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToLoginFragment()
                        mNavController.navigate(action)

//                        var action = ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToLoginFragment2()
//                        mNavController.navigate(action)
                    }else{
                        Log.d("testmail", email)
                        Log.d("testmail", "fun failed")
                        Toast.makeText(this.context,it.exception?.message.toString(),Toast.LENGTH_LONG).show()


                    }
                }

            }
        }

    }



}