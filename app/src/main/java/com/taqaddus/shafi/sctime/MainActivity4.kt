package com.taqaddus.shafi.sctime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        var oncall=findViewById<Button>(R.id.oncll)
        var onscc=findViewById<Button>(R.id.onsc)
        oncall.setOnClickListener(){
            intent=Intent(applicationContext,MainActivity3::class.java)
            startActivity(intent)
        }
        onscc.setOnClickListener(){
            intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}