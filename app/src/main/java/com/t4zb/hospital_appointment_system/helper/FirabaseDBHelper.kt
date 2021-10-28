package com.t4zb.hospital_appointment_system.helper

import com.google.firebase.database.*
import com.t4zb.hospital_appointment_system.util.FirabaseConstants

object FirabaseDBHelper {
    fun rootRef(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }
    fun userPatientRef(): DatabaseReference{
        return rootRef().child(FirabaseConstants.userPatientRef)
    }

    fun userDoctorRef(): DatabaseReference{
        return rootRef().child(FirabaseConstants.userDoctorRef)
    }

    fun appointment():DatabaseReference{
        return rootRef().child(FirabaseConstants.appointment)
    }









  /*  fun setFirebaseRecycler(recyclerView: RecyclerView) {
        val options = FirebaseRecyclerOptions.Builder<UserModelDoctor>()
            .setQuery(userDoctorRef(), UserModelDoctor::class.java).build()
        val adapterFire =
            object : FirebaseRecyclerAdapter<UserModelDoctor, FirabaseDoctorViewHolder>(options) {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): FirabaseDoctorViewHolder {
                    //Inflater
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_doctor, parent, false)
                    return FirabaseDoctorViewHolder(view)
                }

                override fun onBindViewHolder(
                    holderPopularity: FirabaseDoctorViewHolder,
                    position: Int,
                    model: UserModelDoctor
                ) {
                    // Click item id
                    val lisResUID = getRef(position).key
                    holderPopularity.bindUI(model)
                }
            }
        adapterFire.startListening()
        recyclerView.adapter = adapterFire
    }
    */


}