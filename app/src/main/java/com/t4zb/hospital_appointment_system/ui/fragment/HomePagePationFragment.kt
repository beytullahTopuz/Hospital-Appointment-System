package com.t4zb.hospital_appointment_system.ui.fragment

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.databinding.FragmentHomePagePationBinding
import com.t4zb.hospital_appointment_system.helper.FirabaseDBHelper
import com.t4zb.hospital_appointment_system.model.AppointmentModel
import com.t4zb.hospital_appointment_system.model.UserModelDoctor
import com.t4zb.hospital_appointment_system.ui.adapter.AppointmentAdapter
import com.t4zb.hospital_appointment_system.ui.adapter.DoctorsAdapter
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState
import com.t4zb.hospital_appointment_system.util.FirabaseConstants


class HomePagePationFragment : Fragment() {


    private lateinit var mContext: FragmentActivity
    private lateinit var mBindind: FragmentHomePagePationBinding
    var doctorList: ArrayList<UserModelDoctor> = ArrayList()
    var appointmentList: ArrayList<AppointmentModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBindind = FragmentHomePagePationBinding.bind(view)



        mBindind.signoutBtn.setOnClickListener {
            //signout yapilacak
            var directions =
                HomePagePationFragmentDirections.actionHomePagePationFragmentToLoginFragment()
            findNavController().navigate(directions)
        }

        mBindind.addRandevu.setOnClickListener {

            var directions =
                HomePagePationFragmentDirections.actionHomePagePationFragmentToAppointmentFragment()
            findNavController().navigate(directions)
        }

        mBindind.rcyclerViewDoctors.layoutManager = LinearLayoutManager(mContext)
        mBindind.rcyclerViewDoctors.setHasFixedSize(true)

        mBindind.rcyclerViewRandevu.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)


        var id: String = FirebaseAuth.getInstance().currentUser?.uid.toString()
        FirabaseDBHelper.appointment().child(FirabaseConstants.patient).child(id)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        appointmentList.clear()
                        for (snap in snapshot.children){
                            val appointmentModel = snap.getValue(AppointmentModel::class.java)
                            if (appointmentModel != null){
                                appointmentList.add(appointmentModel)
                            }
                        }
                        // recyceler view e veri gönderilecek
                        mBindind.rcyclerViewRandevu.adapter = AppointmentAdapter(appointmentList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        FirabaseDBHelper.userDoctorRef().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    doctorList.clear()
                    for (snap in snapshot.children) {
                        val doctorUser = snap.getValue(UserModelDoctor::class.java)
                        if (doctorUser != null) {
                            doctorList.add(doctorUser)
                        }
                    }
                    mBindind.rcyclerViewDoctors.adapter = DoctorsAdapter(doctorList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        //   FirabaseDBHelper.setFirebaseRecycler(mBindind.rcyclerViewDoctors)
        // FirabaseDBHelper.setFirebaseRecycler(mBindind.rcyclerViewRandevu)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page_pation, container, false)
    }

    companion object {

    }
}