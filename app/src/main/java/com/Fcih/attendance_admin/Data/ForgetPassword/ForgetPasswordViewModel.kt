package com.Fcih.attendance_admin.Data.ForgetPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordViewModel : ViewModel() {

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess
    private val _error = MutableLiveData<String>()
    val error : LiveData<String>
        get() = _error


    fun forgetPassword(email: String, auth: FirebaseAuth) {

        if (email.isNotEmpty()) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    _isSuccess.value = true
                    _error.value="Success"
                }else{
                    _error.value= it.exception?.message
                }
            }

        }else{
            _error.value=" empty E-mail , you have to enter an E-mail"
        }
    }
    fun done(){
        _isSuccess.value=false
    }
}