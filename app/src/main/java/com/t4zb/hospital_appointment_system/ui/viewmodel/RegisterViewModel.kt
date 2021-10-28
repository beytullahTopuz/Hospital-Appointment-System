package com.t4zb.hospital_appointment_system.ui.viewmodel

import android.content.Context
import android.nfc.Tag
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.*
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.helper.GmsRegisterHelper
import com.t4zb.hospital_appointment_system.ui.fragment.RegisterFragment
import com.t4zb.hospital_appointment_system.ui.fragment.RegisterFragmentDirections
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState.ISUSERCONNECTED
import com.t4zb.hospital_appointment_system.util.showLogDebug

import com.t4zb.hospital_appointment_system.util.showToast

class RegisterViewModel : ViewModel(){

    var name: Editable? = null
    var surName: Editable? = null
    var email: Editable? = null
    var password: Editable? = null

    var mcontext: Context? = null




    var result: MutableLiveData<Boolean> = MutableLiveData()

    private fun formValidate(): Boolean {
        if (name.toString().isEmpty() || name == null || surName.toString()
                .isEmpty() || surName == null || email.toString()
                .isEmpty() || email == null || password.toString().isEmpty() || password == null
        ) {
            mcontext?.let { showToast(it, "tüm alanlar dolu değil") }
            return false
        }
        return true
    }


    fun onRegisterClict() {
        if (!formValidate()) {
            return
        } else {
            mcontext?.let {
                GmsRegisterHelper().register(
                    name.toString(),
                    surName.toString(),
                    email.toString(),
                    password.toString(),
                    it
                )
            }
        }
    }

    companion object {
        const val TAG = "REGSTER VIEWMODEL"
    }



}