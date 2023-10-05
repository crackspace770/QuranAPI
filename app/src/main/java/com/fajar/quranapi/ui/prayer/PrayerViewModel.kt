package com.fajar.quranapi.ui.prayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class PrayerViewModel:ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> get() = _counter

    private val timer = Timer()

    init {
        // Simulate real-time updates by incrementing the counter every second
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                _counter.postValue(_counter.value?.plus(1) ?: 0)
            }
        }, 1000, 1000)
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}