/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/19/19 12:26 PM
 */
package com.sam43.android_networking.dagger_test

import javax.inject.Inject

class Car @Inject constructor(wheelsObj: Wheels, engineObj: Engine) {
    private val engine: Engine = engineObj
    private val wheels: Wheels = wheelsObj

    fun drive(): String {
        return "Driving home...."
    }
}