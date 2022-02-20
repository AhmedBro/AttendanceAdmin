package com.Fcih.attendance_admin.View.Data.Login


import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel() : ViewModel() {

    public fun login(email: String, password: String, application: Application , auth: FirebaseAuth) {
        if (password.length > 5 && email.length > 5) {
            auth.signInWithEmailAndPassword(
                email,
                password
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LoginViewModel", "signInWithEmail:success")
                    Toast.makeText(application.applicationContext, "success", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Log.d("LoginViewModel", "signInWithEmail:failed")
                    Toast.makeText(application.applicationContext, "fail", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        } else {
            Toast.makeText(application.applicationContext, "invalid input", Toast.LENGTH_SHORT)
                .show()
        }
    }

}