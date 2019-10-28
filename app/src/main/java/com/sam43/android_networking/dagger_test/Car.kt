package com.sam43.android_networking.dagger_test

import javax.inject.Inject

class Car @Inject constructor(wheelsObj: Wheels, engineObj: Engine) {
    private val engine: Engine = engineObj
    private val wheels: Wheels = wheelsObj

    fun drive(): String {
        return "Driving home...."
    }
}