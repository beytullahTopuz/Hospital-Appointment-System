package com.t4zb.hospital_appointment_system.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.t4zb.hospital_appointment_system.model.UserModelDoctor

object UserModelState {

    fun getFirebaseUser() = FirebaseAuth.getInstance().currentUser

    var ISUSERCONNECTED: MutableLiveData<Boolean> = MutableLiveData()

    var USERTYPE: MutableLiveData<String> = MutableLiveData()

    var DOCTORTYPE: MutableLiveData<String> = MutableLiveData()

    var ISFAIL: MutableLiveData<Boolean> = MutableLiveData()



}