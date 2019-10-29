/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/19/19 5:55 PM
 */
package com.sam43.android_networking.dagger_test

import com.sam43.android_networking.MainActivity
import dagger.Component

@Component
interface MyComponents {
    fun poke(app: MainActivity)
}