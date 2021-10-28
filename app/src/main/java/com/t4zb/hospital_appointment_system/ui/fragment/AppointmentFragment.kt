package com.t4zb.hospital_appointment_system.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.databinding.FragmentAppointmentBinding
import com.t4zb.hospital_appointment_system.helper.FirabaseDBHelper
import com.t4zb.hospital_appointment_system.helper.GmsAppointmetHelper
import com.t4zb.hospital_appointment_system.model.AppointmentModel
import com.t4zb.hospital_appointment_system.model.UserModelDoctor
import com.t4zb.hospital_appointment_system.ui.adapter.DoctorsAdapter
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState
import com.t4zb.hospital_appointment_system.util.Constants
import com.t4zb.hospital_appointment_system.util.showLogDebug


class AppointmentFragment : Fragment() {

    private lateinit var mContext: FragmentActivity
    private lateinit var mBinding: FragmentAppointmentBinding
    var doctorList: ArrayList<UserModelDoctor> = ArrayList()
    var doctorNameList: ArrayList<String> = ArrayList()
    var selectedDRIndex: MutableLiveData<Int> = MutableLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentAppointmentBinding.bind(view)
        selectedDRIndex.value = 0



        FirabaseDBHelper.userDoctorRef().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        val doctorUser = snap.getValue(UserModelDoctor::class.java)
                        if (doctorUser != null) {
                            doctorList.add(doctorUser)
                            doctorUser.name?.let { doctorNameList.add(it) }
                        }
                    }

                    initializeSpinner()


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val builder = AlertDialog.Builder(mContext)
        builder.setTitle("Randevu Confilm")


        mBinding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }


        mBinding.btn9.setOnClickListener {
            builder.setMessage("At 09:00")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                var appointmentModel = AppointmentModel(
                    appointmentID = "",
                    date = "09:00",
                    patientID = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                    doctorID = doctorList[selectedDRIndex.value!!].uid
                )
                GmsAppointmetHelper().createAppointment(appointmentModel)
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }

            builder.show()
        }
        mBinding.btn10.setOnClickListener {
            builder.setMessage("At 10:00")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                var appointmentModel = AppointmentModel(
                    appointmentID = "",
                    date = "10:00",
                    patientID = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                    doctorID = doctorList[selectedDRIndex.value!!].uid
                )
                GmsAppointmetHelper().createAppointment(appointmentModel)
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }

            builder.show()
        }
        mBinding.btn11.setOnClickListener {
            builder.setMessage("At 11:00")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                var appointmentModel = AppointmentModel(
                    appointmentID = "",
                    date = "11:00",
                    patientID = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                    doctorID = doctorList[selectedDRIndex.value!!].uid
                )
                GmsAppointmetHelper().createAppointment(appointmentModel)
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }

            builder.show()
        }
        mBinding.btn12.setOnClickListener {
            builder.setMessage("At 12:00")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                var appointmentModel = AppointmentModel(
                    appointmentID = "",
                    date = "12:00",
                    patientID = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                    doctorID = doctorList[selectedDRIndex.value!!].uid
                )
                GmsAppointmetHelper().createAppointment(appointmentModel)
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }

            builder.show()
        }
        mBinding.btn13.setOnClickListener {
            builder.setMessage("At 13:00")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                var appointmentModel = AppointmentModel(
                    appointmentID = "",
                    date = "13:00",
                    patientID = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                    doctorID = doctorList[selectedDRIndex.value!!].uid
                )
                GmsAppointmetHelper().createAppointment(appointmentModel)
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }

            builder.show()
        }
        mBinding.btn14.setOnClickListener {
            builder.setMessage("At 14:00")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->


                var appointmentModel = AppointmentModel(
                    appointmentID = "",
                    date = "14:00",
                    patientID = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                    doctorID = doctorList[selectedDRIndex.value!!].uid
                )
                GmsAppointmetHelper().createAppointment(appointmentModel)
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }

            builder.show()
        }
        mBinding.btn15.setOnClickListener {
            builder.setMessage("At 15:00")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                var appointmentModel = AppointmentModel(
                    appointmentID = "",
                    date = "15:00",
                    patientID = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                    doctorID = doctorList[selectedDRIndex.value!!].uid
                )
                GmsAppointmetHelper().createAppointment(appointmentModel)
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }

            builder.show()
        }
        mBinding.btn16.setOnClickListener {
            builder.setMessage("At 16:00")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                var appointmentModel = AppointmentModel(
                    appointmentID = "",
                    date = "16:00",
                    patientID = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                    doctorID = doctorList[selectedDRIndex.value!!].uid
                )
                GmsAppointmetHelper().createAppointment(appointmentModel)
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }

            builder.show()
        }


    }

    fun initializeSpinner() {

        var adapter =
            ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, doctorNameList)


        mBinding.spinnerDoctorSelected.adapter = adapter


        mBinding.spinnerDoctorSelected.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                selectedDRIndex.value = position
                showLogDebug(TAG, "secilen : ${doctorNameList[position]}")

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment, container, false)
    }

    companion object {

        const val TAG = "appointment fragment"
    }
}