package com.fajar.quranapi.ui.setting

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.fajar.quranapi.R
import com.fajar.quranapi.ui.prayer.PrayerActivity
import com.fajar.quranapi.ui.utils.NOTIFICATION_CHANNEL_ID

class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val channelId = inputData.getString(NOTIFICATION_CHANNEL_ID)
        if (channelId != null) {
            showNotification("Prayer Time", channelId)
        }
        return Result.success()
    }

    private fun showNotification(prayerTime: String, channelId: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = buildNotification(prayerTime, channelId)

        val notificationId = 1 // Unique ID for the notification
        notificationManager.notify(notificationId, notification)
    }

    private fun buildNotification(prayerTime: String, channelId: String): Notification {
        val intent = Intent(applicationContext, PrayerActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Prayer Time")
            .setContentText("Time for $prayerTime prayer")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
    }
}