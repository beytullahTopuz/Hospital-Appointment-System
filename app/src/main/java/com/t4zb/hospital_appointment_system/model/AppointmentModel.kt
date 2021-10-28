package com.t4zb.hospital_appointment_system.model

data class AppointmentModel(
    val appointmentID:String?=null,
    val date:String?=null,
    val patientID:String?=null,
    val doctorID:String?=null
)
