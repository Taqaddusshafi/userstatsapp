package com.taqaddus.shafi.sctime

import java.util.Date
import android.app.AppOpsManager
import android.app.AppOpsManager.MODE_ALLOWED
import android.app.AppOpsManager.OPSTR_GET_USAGE_STATS
import android.app.usage.UsageEvents
import android.app.usage.UsageStats
import java.time.Duration
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var tvUsageStats:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var reciver: air
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvUsageStats=findViewById(R.id.tv)
        val i = Intent(this, air::class.java)
        this.startService(i)

        if(checkUsageStatsPerimission()){
            showtime2()

        }
        else{
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }
    }


   fun showtime2(){
        val start = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val end =  System.currentTimeMillis()

       val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val stats = usageStatsManager.queryAndAggregateUsageStats((start), (end))
       val n=UsageEvents.Event.SCREEN_INTERACTIVE
       val m=UsageEvents.Event.SCREEN_NON_INTERACTIVE

       var total  = Duration.ofMillis(stats.values.map { it.totalTimeInForeground}.sum())

        tvUsageStats.setText("YOU SPENT ${(total.toMinutes())} mins.")
    }


    private  fun showUsageStats(){
        var cal:java.util.Calendar= java.util.Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH,-1)
        val start = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val end = ZonedDateTime.now().toInstant().toEpochMilli()
        var usageStatsManager:UsageStatsManager=getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        var queryUsageStats:List<UsageStats> =usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,start,end)
        var stats_data:String =""
        for(i in 0..queryUsageStats.size-1) {
            stats_data=stats_data +queryUsageStats.get(i).packageName+"\n"+
                    "Total screen on time:- " +convertTime2( queryUsageStats.get(i).totalTimeInForeground)
        }
        tvUsageStats.setText(stats_data)
    }
    private fun convertTime(lastTimeUsed:Long):String{
        var date:Date =Date(lastTimeUsed)
        var format:SimpleDateFormat= SimpleDateFormat("dd//mm//yyyy hh:mm a",Locale.ENGLISH)
        return format.format(date)
        
    }
    private fun convertTime2(lastTimeUsed:Long):String{
        var date:Date =Date(lastTimeUsed)
        var format:SimpleDateFormat= SimpleDateFormat(" hh:mm ",Locale.ENGLISH)
        return format.format(date)

    }

    private fun checkUsageStatsPerimission(): Boolean {
        var appOpsManager: AppOpsManager ?=null
        var mode:Int =0
        appOpsManager=getSystemService(Context.APP_OPS_SERVICE)!!as AppOpsManager
        mode = appOpsManager.checkOpNoThrow(OPSTR_GET_USAGE_STATS,Process.myUid(),packageName)
        return mode == MODE_ALLOWED

    }
}