package com.t4zb.hospital_appointment_system.ui.viewmodel

import android.content.Context
import android.text.Editable
import androidx.lifecycle.ViewModel
import com.t4zb.hospital_appointment_system.helper.GmsLoginHelper
import com.t4zb.hospital_appointment_system.util.showToast

class LoginViewModel : ViewModel() {

    var email: Editable? = null
    var password: Editable? = null

    var mcontext: Context? = null

    fun formValidate(): Boolean {
        if (email.toString().isEmpty() || email == null || password.toString()
                .isEmpty() || password == null
        ) {
            mcontext?.let { showToast(it, "tüm alanlar dolu değil") }
            return false
        }
        return true
    }

    fun onLoginClicted() {
        if (!formValidate()) {
            return
        } else {
            // start to login
            mcontext?.let { GmsLoginHelper().login(email.toString(), password.toString(), it) }
        }
    }

    companion object{
        const val TAG= "LOGIN VIEWMODEL"
    }
}