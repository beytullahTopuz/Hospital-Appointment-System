package com.t4zb.hospital_appointment_system.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.databinding.FragmentHomePageDoctorBinding
import com.t4zb.hospital_appointment_system.helper.FirabaseDBHelper
import com.t4zb.hospital_appointment_system.model.AppointmentModel
import com.t4zb.hospital_appointment_system.ui.adapter.AppionmentForDoctorAdapter
import com.t4zb.hospital_appointment_system.util.FirabaseConstants
import com.t4zb.hospital_appointment_system.util.showLogDebug


class HomePageDoctorFragment : Fragment() {

    private lateinit var mContext: FragmentActivity
    private lateinit var mBindind: FragmentHomePageDoctorBinding

    val appointmentList: ArrayList<AppointmentModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBindind = FragmentHomePageDoctorBinding.bind(view)

        mBindind.recycerViewforDoctor.layoutManager = LinearLayoutManager(mContext)

        mBindind.signoutBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        FirebaseAuth.getInstance().currentUser?.email.also { mBindind.doctoNameTextView.text = it }



        FirebaseAuth.getInstance().currentUser?.let {
            FirabaseDBHelper.appointment().child(FirabaseConstants.doctor).child(it.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (snap in snapshot.children) {
                                val temp = snap.getValue(AppointmentModel::class.java)
                                if (temp != null) {
                                    appointmentList.add(temp)
                                }
                            }
                            mBindind.recycerViewforDoctor.adapter =
                                AppionmentForDoctorAdapter(appointmentList)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page_doctor, container, false)
    }

    companion object {

        const val TAG = "HomePageDoctor"
    }
}