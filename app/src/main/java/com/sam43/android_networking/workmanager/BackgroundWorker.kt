/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/29/19 3:33 AM
 */
package com.sam43.android_networking.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.sam43.android_networking.utils.getAllFavorites

class BackgroundWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        whatToDo()
        return Result.success()
    }

    private fun whatToDo() {
        /**
         * call api which will run in background silently like suspend function / flow
         * Add methods which are responsible for db queries (Room etc.)
         * do some task with the worker requesting to WorkManager
         * */
        Log.d("whatToDo", "list: ${context.getAllFavorites()}")
    }

}