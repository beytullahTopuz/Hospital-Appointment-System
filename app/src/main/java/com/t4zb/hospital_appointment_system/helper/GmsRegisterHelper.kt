package com.t4zb.hospital_appointment_system.helper

import android.content.Context

import com.google.firebase.auth.FirebaseAuth
import com.t4zb.hospital_appointment_system.model.UserModelDoctor
import com.t4zb.hospital_appointment_system.model.UserModelPatient
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState
import com.t4zb.hospital_appointment_system.util.Constants
import com.t4zb.hospital_appointment_system.util.showLogDebug
import com.t4zb.hospital_appointment_system.util.showLogError
import com.t4zb.hospital_appointment_system.util.showToast

class GmsRegisterHelper {

    fun register(
        name: String,
        surname: String,
        email: String,
        password: String,
        mcontext: Context
    ) {


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { authResult ->


                if (authResult.isSuccessful) {
                    showLogDebug(TAG, "register successful")

                    // save to db

                    var uid: String? = FirebaseAuth.getInstance().uid
                    if (uid == null) {
                        uid = ""
                    }


                    if (UserModelState.USERTYPE.value == Constants.USERTYPEPATIENT) {
                        var userModelPatient = UserModelPatient(
                            name,
                            surname,
                            email,
                            uid
                        )

                        GmsUserHelper.insertPatientUser(userModelPatient)
                    } else {

                        var userModelDoctor = UserModelDoctor(
                            name,
                            surname,
                            email,
                            uid,
                            UserModelState.DOCTORTYPE.value!!
                        )
                        GmsUserHelper.insertDoctorUser(userModelDoctor)
                    }


                } else {

                    showToast(mcontext, "Register failed")
                    showLogError(TAG, "register failed")
                }
            }


    }

    companion object {
        const val TAG = "REGISTER HELPER"
    }

}