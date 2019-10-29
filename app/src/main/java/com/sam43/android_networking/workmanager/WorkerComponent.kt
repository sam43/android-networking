/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/29/19 3:30 AM
 */
package com.sam43.android_networking.workmanager

import com.sam43.android_networking.ui.dashboard.DashboardFragment
import dagger.Component

@Component
interface WorkerComponent {
    fun push(fragment: DashboardFragment)
}