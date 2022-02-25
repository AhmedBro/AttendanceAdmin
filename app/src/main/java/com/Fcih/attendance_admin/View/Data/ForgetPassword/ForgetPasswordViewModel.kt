package com.Fcih.attendance_admin.View.Data.ForgetPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordViewModel : ViewModel() {

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    fun forgetPassword(email: String, auth: FirebaseAuth) {

        if (email.isNotEmpty()) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    _isSuccess.value = true
                }
            }

        }
    }
}