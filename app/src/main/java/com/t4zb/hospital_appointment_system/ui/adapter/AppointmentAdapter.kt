package com.t4zb.hospital_appointment_system.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.model.AppointmentModel

class AppointmentAdapter(private val appintmentList: ArrayList<AppointmentModel>) :
    RecyclerView.Adapter<AppointmentAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewDoctorName: TextView = itemView.findViewById(R.id.textViewDoctorName)
       // val textViewType: TextView = itemView.findViewById(R.id.textViewType)
        val textViewDate:TextView = itemView.findViewById(R.id.textViewDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.appointment_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = appintmentList[position]


        holder.textViewDate.text = currentItem.date



    }

    override fun getItemCount(): Int {
        return appintmentList.size
    }
}