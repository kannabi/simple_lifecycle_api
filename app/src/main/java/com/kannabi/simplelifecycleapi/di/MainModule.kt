package com.kannabi.simplelifecycleapi.di

import com.kannabi.simplelifecycleapi.IDummyPresenter
import com.kannabi.simplelifecycleapi.di.scopes.MainScope
import com.kannabi.simplelifecycleapi.model.DummyModel
import com.kannabi.simplelifecycleapi.presenter.DummyPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @MainScope @Provides
    fun provideDummyPresenter(): IDummyPresenter = DummyPresenter(DummyModel())
}