package com.taqaddus.shafi.sctime

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class  air:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val TIME_ERROR: Long = 1000
        Log.i("[BroadcastReceiver]", "MyReceiver")
        if (intent.action == Intent.ACTION_SCREEN_ON) {
            var startTimer = System.currentTimeMillis()

        } else if (intent.action == Intent.ACTION_SCREEN_OFF) {
           var  endTimer = System.currentTimeMillis()
            var screenOnTime = endTimer - System.currentTimeMillis()
            if (screenOnTime < TIME_ERROR) {
                screenOnTime+=screenOnTime
            }
            var msg =screenOnTime

            val i = Intent(context, MainActivity2::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("message", msg)
            context!!.startActivity(i)

        }
    }
}