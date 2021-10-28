package com.t4zb.hospital_appointment_system.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.model.AppointmentModel

class AppionmentForDoctorAdapter(private val appintmentList: ArrayList<AppointmentModel>) :
    RecyclerView.Adapter<AppionmentForDoctorAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val pationName: TextView = itemView.findViewById(R.id.pationName)
        val pationDate: TextView = itemView.findViewById(R.id.pationDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.appointmrent_for_doctor_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem = appintmentList[position]

        holder.pationDate.text = currentItem.date
        //pation date verisi çekilecek
        holder.pationName.text = currentItem.patientID
    }

    override fun getItemCount(): Int {
        return appintmentList.size
    }
}