/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/28/19 11:52 PM
 */
package com.sam43.android_networking.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CircleProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val backgroundWidth = 10f
    private val progressWidth = 10f

    private val progressPaint = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        strokeWidth = progressWidth
        isAntiAlias = true
    }

    private val backgroundPaint = Paint().apply {
        color = Color.parseColor("#C35D59")
        style = Paint.Style.STROKE
        strokeWidth = backgroundWidth
        isAntiAlias = true
    }

    var progress: Float = 100f
        set(value) {
            field = value
            invalidate()
        }

    private val oval = RectF()
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var radius: Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = w.toFloat() / 2
        centerY = h.toFloat() / 2
        radius = w.toFloat() / 2 - progressWidth
        oval.set(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(centerX, centerY, radius, backgroundPaint)
        canvas?.drawArc(oval, -90f, -360f * progress, false, progressPaint)
    }
}