package com.t4zb.hospital_appointment_system.helper

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState.ISFAIL
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState.ISUSERCONNECTED
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState.USERTYPE
import com.t4zb.hospital_appointment_system.util.Constants
import com.t4zb.hospital_appointment_system.util.showLogDebug
import com.t4zb.hospital_appointment_system.util.showLogError
import com.t4zb.hospital_appointment_system.util.showToast

class GmsLoginHelper {


    fun login(email: String, password: String, mcontext: Context) {
        //pass
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    //getDB
                    var tempUID = authResult.result?.user?.uid.toString()
                    findUser(tempUID, mcontext)
                } else {
                    ISUSERCONNECTED.value = false
                    ISFAIL.value = true
                    val error = authResult.exception?.message.toString()
                    showToast(mcontext, "LOGIN FAILED, {${error}}")
                }
            }
    }

    private fun findUser(uid: String, context: Context) {

        if (UserModelState.USERTYPE.value == Constants.USERTYPEDOCTOR){
            FirabaseDBHelper.userDoctorRef().child(uid).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    var doc = it.result
                    // doktor login
                    ISUSERCONNECTED.value = true
                    USERTYPE.value = Constants.USERTYPEDOCTOR
                    ISFAIL.value = false
                    showLogDebug(TAG,"doktor girişi başarili")
                    // needed user model
                }
            }
        }else if (UserModelState.USERTYPE.value == Constants.USERTYPEPATIENT){
            FirabaseDBHelper.userPatientRef().child(uid).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    //patient login
                    ISUSERCONNECTED.value = true
                    USERTYPE.value = Constants.USERTYPEPATIENT
                    ISFAIL.value = false
                    showLogDebug(TAG,"hasta girişi başarili")
                    // needed user model
                }
            }
        }else {
            // error - user not found
            ISUSERCONNECTED.value = false
            ISFAIL.value = true
            showLogDebug(TAG, "Unknow Error")
            showToast(context, "Error")

        }


    }


    companion object {
        const val TAG = "GMS LOGIN HELPER"
    }
}