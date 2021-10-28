package com.t4zb.hospital_appointment_system.util

import android.content.Context
import android.os.Handler
import android.util.Log
import android.widget.Toast

fun showLogError(tag: String, string: String) {
    Log.i(tag, "showLogError: $string")
}

fun showLogDebug(tag: String, string: String) {
    Log.d(tag, "showLogDebug: $string")
}

fun showToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()

}
