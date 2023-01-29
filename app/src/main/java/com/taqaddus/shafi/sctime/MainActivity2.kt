package com.taqaddus.shafi.sctime

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var reciver: air
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val i = Intent(this, air::class.java)
        this.startService(i)
        var v2=findViewById<TextView>(R.id.textView)
        reciver= air()
        val lockFilter = IntentFilter()
        lockFilter.addAction(Intent.ACTION_SCREEN_ON)
        lockFilter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(reciver, lockFilter)
        val intent = intent
        val message = intent.getStringExtra("message")
        v2.setText(message)



    }
}