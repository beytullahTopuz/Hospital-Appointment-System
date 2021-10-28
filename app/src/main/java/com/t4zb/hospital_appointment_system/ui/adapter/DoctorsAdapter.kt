package com.t4zb.hospital_appointment_system.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.model.UserModelDoctor

class DoctorsAdapter(private val doctorList: ArrayList<UserModelDoctor>) :
    RecyclerView.Adapter<DoctorsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewDoctorName: TextView = itemView.findViewById(R.id.textViewDoctorName)
        val textViewTypeName: TextView = itemView.findViewById(R.id.textViewTypeName)
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = doctorList[position]

        holder.textViewTypeName.text = currentItem.doctorType
        holder.textViewDoctorName.text = currentItem.name
        holder.textViewDate.tag = currentItem.email
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }
}