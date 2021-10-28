package com.t4zb.hospital_appointment_system.helper

import com.t4zb.hospital_appointment_system.model.AppointmentModel
import com.t4zb.hospital_appointment_system.util.FirabaseConstants
import com.t4zb.hospital_appointment_system.util.showLogDebug


class GmsAppointmetHelper {


    fun createAppointment(appointmentModel: AppointmentModel) {

        appointmentModel.doctorID?.let {
            var db = FirabaseDBHelper.appointment().child(FirabaseConstants.doctor).child(it)
            var key = db.push().key.toString()
            db.child(key)
                .setValue(appointmentModel)
                .addOnCompleteListener { taskResult ->
                    if (taskResult.isSuccessful) {
                        showLogDebug(TAG, "dr successful")
                    }
                }
        }
        appointmentModel.patientID?.let {
            var db = FirabaseDBHelper.appointment().child(FirabaseConstants.patient).child(it)
            var key = db.push().key.toString()
            db.child(key).setValue(appointmentModel)
                .addOnCompleteListener { taskResult ->
                    if (taskResult.isSuccessful) {
                        showLogDebug(TAG, "patint successful")
                    }
                }
        }
    }

    fun getAppointmet(){
       // FirabaseDBHelper.appointment().child(FirabaseConstants.doctor).
    }

    companion object {
        const val TAG = "GMS APPOINTMENT"
    }
}