package com.example.appinboxbasic

import android.app.NotificationManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.clevertap.android.sdk.CTInboxListener
import com.clevertap.android.sdk.CleverTapAPI


class MainActivity : AppCompatActivity(), CTInboxListener {
    override fun onCreate(savedInstanceState: Bundle?) {

        var clevertapDefaultInstance: CleverTapAPI? = null
		clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)   //Initializing the CleverTap SDK
        CleverTapAPI.setDebugLevel(3)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
		CleverTapAPI.createNotificationChannel(getApplicationContext(), "generic", "mychannel", "lDescription", NotificationManager.IMPORTANCE_MAX, true)        //added by CleverTap Assistant
        clevertapDefaultInstance?.apply {

            ctNotificationInboxListener = this@MainActivity

            //Initialize the inbox and wait for callbacks on overridden methods
            initializeInbox()
        }

    }

    override fun inboxDidInitialize() {
        val button: Button = findViewById(R.id.button_id)
        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v:View) {
                var clevertapDefaultInstance: CleverTapAPI? = null
                clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)   //Initializing the CleverTap SDK

                clevertapDefaultInstance?.showAppInbox()//Opens Activity with default style config
            }
        })
    }

    override fun inboxMessagesDidUpdate() {

    }
}
