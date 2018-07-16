package com.app.practica_kotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.content.res.TypedArrayUtils.getString
import android.view.View

class ExampleNotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_notification)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun courseUpdate(view:View){
        val id = "my_channel_01"
        //the user-visible name of the channel
        val name = getString(R.string.abc_action_bar_home_description)
        // the user-visible description of the cahnnel
        val description = getString(R.string.abc_action_bar_home_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(id, name, importance)
        //Configure the notification channel
        mChannel.description = description
        mChannel.enableLights(true)
        //Sets the notification light color for notifications posted to this channel, if device supports this feature
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)
        // the id of the channel
        val CHANNEL_ID = "my_channel_02"
        //Use notiifcationCompatBuilder to add the notifcation objects
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("My firts notification")
                .setContentText("Look at my new Activity")
        //Creates on explicit intent for an Activity in your app
        val resultIntent = Intent(this, ResultActivity::class.java)
        //The stack builder object will contain an actificail back stack for thestarted activity
        //This ensures thaht navigating backward from the activity leads out of your app to the Home screen
        val stackBuilder = TaskStackBuilder.create(this)
        //Adds the intent that starts the activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder.setContentIntent(resultPendingIntent)
        val mNotificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //mNotificationId is a unique integer your app uses t identify the notification.
        //For example, to cancel the notification, you can pass its ID number to NotificationManager.cancel()
        mNotificationManager.notify(1, mBuilder.build())
    }
}
