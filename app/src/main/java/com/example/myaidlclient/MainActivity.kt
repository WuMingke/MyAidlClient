package com.example.myaidlclient

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import com.example.myaidlserver.IMyAidlInterface

class MainActivity : AppCompatActivity() {

    private var iMyAidlInterface: IMyAidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindService()

        findViewById<Button>(R.id.btn).setOnClickListener {
            try {
                Log.i(
                    "wmkwmk",
                    "btn_click:iMyAidlInterface = ${iMyAidlInterface?.name},iMyAidlInterface.getUserName(1) = ${iMyAidlInterface?.getUserName("1")}"
                )

            } catch (e: Exception) {
                Log.i("wmkwmk", "btn_click_Exception:$e")
            }

        }
    }

    private var serviceConnection: ServiceConnection? = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("wmkwmk", "ServiceConnection: onServiceConnected")
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("wmkwmk", "ServiceConnection: onServiceDisconnected")
            serviceConnection = null
        }

    }

    private fun bindService() {
        serviceConnection?.let {
            val intent = Intent()
            intent.setClassName("com.example.myaidlserver", "com.example.myaidlserver.MyService")
            bindService(intent, it, BIND_AUTO_CREATE)
            Log.i("wmkwmk", "bindService...")
        }
    }
}