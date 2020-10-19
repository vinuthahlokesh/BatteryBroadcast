package com.example.batterybroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var text_view: TextView
    lateinit var broadcastRecv: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text_view=findViewById(R.id.textview)
        broadcastRecv= object:BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val Batterypercent = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
                text_view.setText("Battery percentage: " + Batterypercent)
                val statusOfBattery = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, 0)
                if (statusOfBattery == BatteryManager.BATTERY_STATUS_CHARGING)
                {
                    Toast.makeText(getApplicationContext(), "Charger is connected", Toast.LENGTH_SHORT).show()
                }
                else if (statusOfBattery == BatteryManager.BATTERY_STATUS_FULL)
                {
                    Toast.makeText(getApplicationContext(), "Battery is fully charged", Toast.LENGTH_SHORT).show()
                }
                else if (statusOfBattery == BatteryManager.BATTERY_STATUS_DISCHARGING)
                {
                    Toast.makeText(getApplicationContext(), "Battery is discharging", Toast.LENGTH_SHORT).show()
                }
                else if (statusOfBattery == BatteryManager.BATTERY_STATUS_NOT_CHARGING)
                {
                    Toast.makeText(getApplicationContext(), "Charger is not connected", Toast.LENGTH_SHORT).show()
                }
                else if (statusOfBattery == BatteryManager.BATTERY_STATUS_UNKNOWN)
                {
                    Toast.makeText(getApplicationContext(), "Battery status is unknown", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
    override fun onStart() {
        registerReceiver(broadcastRecv, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        super.onStart()
    }
    override fun onStop() {
        unregisterReceiver(broadcastRecv)
        super.onStop()
    }
}