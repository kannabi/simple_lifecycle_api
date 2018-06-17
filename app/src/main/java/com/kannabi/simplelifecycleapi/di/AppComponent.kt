package com.kannabi.simplelifecycleapi.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun plusMainComponent(mainModule: MainModule): MainComponent
}