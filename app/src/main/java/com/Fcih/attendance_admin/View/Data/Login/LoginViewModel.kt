package com.Fcih.attendance_admin.View.Data.Login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {


    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean>
        get() = _navigateToHome


    private val _showProgressbar = MutableLiveData<Boolean>()
    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar


    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    fun doneNavigatingToHome() {
        _navigateToHome.value = false
    }


    fun login(
        email: String,
        password: String,
        auth: FirebaseAuth
    ) {
        if (password.isNotEmpty() && email.isNotEmpty()) {
            auth.signInWithEmailAndPassword(
                email,
                password
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _navigateToHome.value = true
                    _showProgressbar.value = false
                } else {
                    _showProgressbar.value = false
                    _errorMessage.value = task.exception?.localizedMessage ?: "Wrong e-mail or password"
                }
            }

        } else {
            _errorMessage.value = "E-mail or password can't be empty"
            _showProgressbar.value = false
        }
    }
}