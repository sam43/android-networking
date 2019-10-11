package com.sam43.android_networking.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment... this is a very long line you can see\n" +
                "Here I broke the line to check if everything is working"
    }
    val text: LiveData<String> = _text
}