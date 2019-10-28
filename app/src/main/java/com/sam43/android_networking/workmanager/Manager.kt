package com.sam43.android_networking.workmanager

import androidx.work.WorkManager
import com.sam43.android_networking.App
import javax.inject.Inject

class Manager @Inject constructor() {
    fun instance(): WorkManager {
        return App.applicationContext().let { WorkManager.getInstance(it) }
    }
}