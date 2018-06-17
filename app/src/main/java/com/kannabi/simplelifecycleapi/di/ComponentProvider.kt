package com.kannabi.simplelifecycleapi.di

import android.content.Context

class ComponentProvider(applicationContext: Context) {
    private var appComponent: AppComponent = DaggerAppComponent
                                        .builder()
                                        .appModule(AppModule(applicationContext))
                                        .build()

    fun getMainComponent() = appComponent.plusMainComponent(MainModule())
}