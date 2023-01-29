package com.taqaddus.shafi.sctime

import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.CallLog
import android.util.Log
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CALL_LOG
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALL_LOG), 111)
        }
        else{
            readLog()}
    }
    private fun readLog(){
        // var lst=findViewById<ListView>(R.id.lstview)
        var edt=findViewById<EditText>(R.id.edtx)
        var cols:Array<String> = arrayOf(CallLog.Calls._ID,CallLog.Calls.NUMBER,CallLog.Calls.TYPE,CallLog.Calls.DURATION,CallLog.Calls.DATE)
        var rs:Cursor?=contentResolver.query(CallLog.Calls.CONTENT_URI,cols,null,null,"${CallLog.Calls.LAST_MODIFIED} DESC" )
        // var from:Array<String> = arrayOf(CallLog.Calls.NUMBER,CallLog.Calls.DURATION,CallLog.Calls.TYPE)
        // var adaptar=SimpleCursorAdapter(this,R.layout.mylayout,rs,from, intArrayOf(R.id.one,R.id.two,R.id.three),0)
        //lst.adapter=adaptar
        while(rs?.moveToNext()!!){
            var data:String ="\nNUMBER ="+ rs.getString(1)
            data+= "\nTYPE ="+ rs.getString(2)
            data+= "\nDURATION ="+ rs.getString(3)
            data+= "\nDATE ="+ rs.getString(4)
            edt.append(data)
        }

    }

    fun onRequestPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readLog()
        }
    }


}