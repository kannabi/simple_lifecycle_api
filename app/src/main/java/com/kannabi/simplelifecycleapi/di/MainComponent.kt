package com.kannabi.simplelifecycleapi.di

import com.awsm_guys.mobileclicker.lifecycleapi.ProvidePresenter
import com.kannabi.simplelifecycleapi.DummyFragment
import com.kannabi.simplelifecycleapi.IDummyPresenter
import com.kannabi.simplelifecycleapi.di.scopes.MainScope
import com.kannabi.simplelifecycleapi.view.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [(MainModule::class)])
interface MainComponent: ProvidePresenter<IDummyPresenter> {
    fun inject(mainActivity: MainActivity)

    fun inject(dummyFragment: DummyFragment)
}