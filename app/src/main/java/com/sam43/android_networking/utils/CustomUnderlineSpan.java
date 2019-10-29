package com.sam43.android_networking.utils;


/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/12/19 11:12 AM
 */

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

public class CustomUnderlineSpan implements LineBackgroundSpan {

    private Paint p;
    private int start, end;

    public CustomUnderlineSpan(int underlineColor, int underlineStart, int underlineEnd) {
        super();
        this.start = underlineStart;
        this.end = underlineEnd;
        p = new Paint();
        p.setColor(underlineColor);
        p.setStrokeWidth(3F);
        p.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {

        if (this.end < start) return;
        if (this.start > end) return;

        int offsetX = 0;
        if (this.start > start) {
            offsetX = (int) p.measureText(text.subSequence(start, this.start).toString());
        }

        int length = (int) p.measureText(text.subSequence(Math.max(start, this.start), Math.min(end, this.end)).toString());
        c.drawLine(offsetX, baseline + 3F, length + offsetX, baseline + 3F, this.p);
    }
}