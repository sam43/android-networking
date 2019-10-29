/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/28/19 11:52 PM
 */
package com.sam43.android_networking.ui.notifications

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.sam43.android_networking.R
import com.sam43.android_networking.utils.formatTimerMillisecond
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import org.jetbrains.anko.support.v4.toast

class NotificationsFragment : Fragment() {

    private lateinit var root: View
    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var timeOutRemoveTimer: CountDownTimer
    private var TOTAL_TIME = 120 * 1000L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)

        //textView.text = car.drive()
        setProgress()
        return root
    }

    private fun setProgress() {
        val startingProgress = TOTAL_TIME - (30 * 1000L)
        root.circle_progress.progress = startingProgress.toFloat()
        timeOutRemoveTimer = object : CountDownTimer(startingProgress, 1) {
            override fun onFinish() {
                root.circle_progress.progress = 1f
            }

            override fun onTick(millisUntilFinished: Long) {
                root.time.text = formatTimerMillisecond(millisUntilFinished)
                root.circle_progress.progress =
                    (TOTAL_TIME - millisUntilFinished).toFloat() / TOTAL_TIME
            }
        }
        timeOutRemoveTimer.start()
    }

    private fun findOutTimeLeft(): Long {
        //if (arguments != null) {
        val expiredAtMills = System.currentTimeMillis().plus(30 * 1000L)
        val timeLeft = expiredAtMills.minus(System.currentTimeMillis())
        toast("left time: ${timeLeft.div(1000)}")
        //}
        return timeLeft
    }
}