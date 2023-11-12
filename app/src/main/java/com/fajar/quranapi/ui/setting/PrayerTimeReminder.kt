package com.fajar.quranapi.ui.setting

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.fajar.quranapi.core.domain.model.Prayer
import com.fajar.quranapi.ui.shalat.prayer.PrayerActivity
import com.fajar.quranapi.ui.utils.CHANNEL_ID
import com.fajar.quranapi.ui.utils.ID_REPEATING
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PrayerTimeReminder : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Open the PrayerActivity
        val prayerIntent = Intent(context, PrayerActivity::class.java)
        // Flags to clear the back stack
        prayerIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(prayerIntent)
    }

    fun setPrayerTimeReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, PrayerTimeReminder::class.java)

        val calendar = Calendar.getInstance()
        // Set the desired time for the reminder
        // Replace with the desired hour and minute
        calendar.set(Calendar.HOUR_OF_DAY, 6)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            ID_REPEATING,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        Toast.makeText(context, "Prayer Time Reminder set up", Toast.LENGTH_SHORT).show()
    }

    fun cancelPrayerTimeReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, PrayerTimeReminder::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            ID_REPEATING,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)
        Toast.makeText(
            context,
            "Prayer Time Reminder Canceled Successfully",
            Toast.LENGTH_SHORT
        ).show()
    }
}