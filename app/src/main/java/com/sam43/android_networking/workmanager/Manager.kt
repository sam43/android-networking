/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/29/19 3:33 AM
 */
package com.sam43.android_networking.workmanager

import androidx.work.WorkManager
import com.sam43.android_networking.App
import javax.inject.Inject

class Manager @Inject constructor() {
    fun instance(): WorkManager {
        return App.applicationContext().let { WorkManager.getInstance(it) }
    }
}