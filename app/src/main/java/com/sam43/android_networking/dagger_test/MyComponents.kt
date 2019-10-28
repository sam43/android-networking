package com.sam43.android_networking.dagger_test

import com.sam43.android_networking.MainActivity
import dagger.Component

@Component
interface MyComponents {
    fun poke(app: MainActivity)
}