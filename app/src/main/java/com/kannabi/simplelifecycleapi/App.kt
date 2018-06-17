package com.kannabi.simplelifecycleapi

import android.app.Application
import com.kannabi.simplelifecycleapi.di.ComponentProvider

class App: Application() {
    lateinit var componentProvider: ComponentProvider

    override fun onCreate() {
        super.onCreate()
        componentProvider = ComponentProvider(this.applicationContext)
    }
}