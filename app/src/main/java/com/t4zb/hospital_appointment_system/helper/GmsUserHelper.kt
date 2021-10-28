package com.t4zb.hospital_appointment_system.helper

import com.t4zb.hospital_appointment_system.helper.FirabaseDBHelper.userDoctorRef
import com.t4zb.hospital_appointment_system.helper.FirabaseDBHelper.userPatientRef
import com.t4zb.hospital_appointment_system.model.UserModelDoctor
import com.t4zb.hospital_appointment_system.model.UserModelPatient
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState.ISFAIL
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState.ISUSERCONNECTED
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState.USERTYPE
import com.t4zb.hospital_appointment_system.util.Constants
import com.t4zb.hospital_appointment_system.util.showLogDebug
import com.t4zb.hospital_appointment_system.util.showLogError

object GmsUserHelper {

    private const val TAG = "GMS USER HELPER"


    fun insertPatientUser(userModelPatient: UserModelPatient) {

        userPatientRef().child(userModelPatient.uid).setValue(userModelPatient)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    showLogDebug(TAG, "Successful")
                    ISUSERCONNECTED.value = true
                    USERTYPE.value = Constants.USERTYPEPATIENT
                    ISFAIL.value = false
                } else {
                    showLogError(TAG, "Fail")
                    ISFAIL.value = true
                    ISUSERCONNECTED.value = false
                }
            }
    }

    fun insertDoctorUser(userModelDoctor: UserModelDoctor) {
        userModelDoctor.uid?.let {
            userDoctorRef().child(it).setValue(userModelDoctor)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        showLogDebug(TAG, "Successful")
                        ISUSERCONNECTED.value = true
                        USERTYPE.value = Constants.USERTYPEDOCTOR
                        ISFAIL .value = false
                    }else{
                        showLogError(TAG, "Fail")
                        ISFAIL.value = true
                        ISUSERCONNECTED.value = false
                    }
                }
        }
    }

}