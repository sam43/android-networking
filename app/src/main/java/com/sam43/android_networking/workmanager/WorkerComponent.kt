package com.sam43.android_networking.workmanager

import com.sam43.android_networking.ui.dashboard.DashboardFragment
import dagger.Component

@Component
interface WorkerComponent {
    fun push(fragment: DashboardFragment)
}